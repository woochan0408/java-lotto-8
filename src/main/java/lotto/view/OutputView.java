package lotto.view;

import lotto.Lotto;
import lotto.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            List<Integer> numbers = lotto.getNumbers();
            numbers.sort(Integer::compareTo);
            System.out.println(numbers);
        }
    }

    public void printStatistics(Map<Rank, Integer> result, double rateOfReturn) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        System.out.printf("3개 일치 (5,000원) - %d개\n", result.getOrDefault(Rank.FIFTH, 0));
        System.out.printf("4개 일치 (50,000원) - %d개\n", result.getOrDefault(Rank.FOURTH, 0));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", result.getOrDefault(Rank.THIRD, 0));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", result.getOrDefault(Rank.SECOND, 0));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", result.getOrDefault(Rank.FIRST, 0));

        System.out.printf("총 수익률은 %.1f%%입니다.\n", rateOfReturn);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
