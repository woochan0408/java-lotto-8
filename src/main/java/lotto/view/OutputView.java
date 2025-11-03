package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            List<Integer> numbers = new java.util.ArrayList<>(lotto.getNumbers());
            numbers.sort(Integer::compareTo);
            System.out.println(numbers);
        }
    }

    public void printStatistics(Map<Rank, Integer> result, double rateOfReturn) {
        System.out.println("\n당첨 통계");
        System.out.println("---");

        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);

        for (Rank rank : ranks) {
            if (rank != Rank.MISS) {
                System.out.printf("%s (%,d원) - %d개\n",
                        rank.getDescription(),
                        rank.getPrize(),
                        result.getOrDefault(rank, 0));
            }
        }

        System.out.printf("총 수익률은 %.1f%%입니다.\n", rateOfReturn);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
