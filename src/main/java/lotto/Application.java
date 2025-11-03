package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        int amount;
        List<Lotto> lottos = new ArrayList<>();

        // 금액 입력 검증
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String input = Console.readLine();
                amount = Integer.parseInt(input);
                validationAmount(amount);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 로또 구매 및 번호 출력
        int lottoCount = amount / 1000;
        System.out.println(" " + lottoCount + "개를 구매했습니다.");
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> lottoNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(lottoNumbers); // 로또 번호 오름차순 정렬
            Lotto lotto = new Lotto(lottoNumbers);
            lottos.add(lotto);
            System.out.println(lotto.getNumbers()); // 구매한 로또 번호 출력
        }

        // 당첨 번호 입력
        WinningLotto winningLotto;
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = Console.readLine();
                String[] parts = input.split(",");
                List<Integer> winningNumber = new ArrayList<>();

                for (String part : parts) {
                    winningNumber.add(Integer.parseInt(part.trim()));
                }

                validateWinningNumber(winningNumber);
                winningLotto = new WinningLotto(winningNumber);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 보너스 번호 입력 및 저장
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String input = Console.readLine();
                int bonusNumber = Integer.parseInt(input);
                validateBonusNumber(bonusNumber, winningLotto.getNumbers());
                winningLotto.setBonusNumber(bonusNumber); // winningLotto 객체에 보너스 번호 저장
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 당첨 통계 계산
        LottoChecker lottoChecker = new LottoChecker();
        Map<Rank, Integer> rankCount = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = lottoChecker.checkRank(lotto, winningLotto);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }

        // 결과 출력
        System.out.println("당첨 통계");
        System.out.println("---");

        // README와 동일한 순서로 출력하기 위한 배열
        Rank[] ranksToShow = {Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST};
        DecimalFormat formatter = new DecimalFormat("###,###");

        long totalPrize = 0;
        for (Rank rank : ranksToShow) {
            int count = rankCount.get(rank);
            totalPrize += (long) rank.getPrize() * count;
            System.out.printf(
                    "%s (%s원) - %d개%n",
                    rank.getDescription(),
                    formatter.format(rank.getPrize()),
                    count
            );
        }

        double profitRate = ((double) totalPrize / amount) * 100;
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }

    public static void validationAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1000으로 나누어떨어지는 금액을 입력해주세요.");
        }
    }

    public static void validateWinningNumber(List<Integer> winningNumber) {
        if (winningNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        List<Integer> validationList = new ArrayList<>();
        for (int number : winningNumber) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
            if (validationList.contains(number)) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복된 숫자가 있습니다.");
            }
            validationList.add(number);
        }
    }

    public static void validateBonusNumber(int bonusNumber, List<Integer> winningNumbers) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
