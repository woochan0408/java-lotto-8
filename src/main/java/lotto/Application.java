package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        int amount;
        List<Lotto> lottos = new ArrayList<>();

        while(true){
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

        int lottoCount = amount / 1000;
        for (int i = 1; i <= lottoCount; i++) {
            List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(lottoNumbers));
        }

        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        String[] parts = input.split(",");
        List<Integer> winningNumber = new ArrayList<>();

        for (String part : parts) {
            winningNumber.add(Integer.parseInt(part.trim()));
        }

    }
    public static void validationAmount(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("[ERROR] 음수는 입력할 수 없습니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1000으로 나누어떨어지는 금액을 입력해주세요.");
        }
    }

    public static void validateWinningNumber(int winningNumber) {}

}