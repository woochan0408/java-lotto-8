package lotto;

import java.util.List;

public class WinningLotto extends Lotto {
    private Integer bonusNumber;

    public WinningLotto(List<Integer> numbers) {
        super(numbers);
    }

    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
