package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoGameController(InputView inputView, OutputView outputView, LottoMachine lottoMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMachine = lottoMachine;
    }

    public void run() {
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = lottoMachine.purchaseLottos(purchaseAmount);
        outputView.printPurchasedLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();

        Map<Rank, Integer> result = calculateStatistics(lottos, winningLotto);
        double rateOfReturn = calculateRateOfReturn(purchaseAmount, result);

        outputView.printStatistics(result, rateOfReturn);
    }

    private int getPurchaseAmount() {
        while (true) {
            try {
                return Integer.parseInt(inputView.readPurchaseAmount());
            } catch (NumberFormatException e) {
                outputView.printErrorMessage("[ERROR] 숫자만 입력 가능합니다.");
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningNumbers = getWinningNumbers();
        while (true) {
            try {
                int bonusNumber = getBonusNumber();
                return new WinningLotto(winningNumbers, bonusNumber);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<Integer> getWinningNumbers() {
        while (true) {
            try {
                String[] numbers = inputView.readWinningNumbers().split(",");
                return Arrays.stream(numbers)
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                outputView.printErrorMessage("[ERROR] 숫자만 입력 가능합니다.");
            }
        }
    }

    private int getBonusNumber() {
        while (true) {
            try {
                return Integer.parseInt(inputView.readBonusNumber());
            } catch (NumberFormatException e) {
                outputView.printErrorMessage("[ERROR] 숫자만 입력 가능합니다.");
            }
        }
    }

    private Map<Rank, Integer> calculateStatistics(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    private double calculateRateOfReturn(int purchaseAmount, Map<Rank, Integer> result) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            totalPrize += (long) entry.getKey().getPrize() * entry.getValue();
        }

        return ((double) totalPrize / purchaseAmount) * 100;
    }
}
