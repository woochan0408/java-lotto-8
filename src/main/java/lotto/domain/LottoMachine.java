package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public List<Lotto> purchaseLottos(int money) {
        validatePurchaseAmount(money);
        int numberOfLottos = money / LOTTO_PRICE;

        return IntStream.range(0, numberOfLottos)
                .mapToObj(i -> new Lotto(generateLottoNumbers()))
                .collect(Collectors.toList());
    }

    protected List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    private void validatePurchaseAmount(int money) {
        if (money <= 0 || money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위의 양수여야 합니다.");
        }
    }
}
