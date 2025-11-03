package lotto.domain;

import java.util.List;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        if (lotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto userLotto) {
        long matchCount = userLotto.getNumbers().stream()
                .filter(lotto.getNumbers()::contains)
                .count();


        boolean hasBonus = userLotto.getNumbers().contains(bonusNumber);

        return Rank.of((int) matchCount, hasBonus);
    }
}

