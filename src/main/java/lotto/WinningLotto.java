package lotto;

import java.util.List;

public class WinningLotto extends Lotto {
    private List<Integer> numbers;
    private Integer bonusNumber;

    public WinningLotto(List<Integer> numbers) {
        super(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
