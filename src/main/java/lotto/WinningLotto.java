package lotto;

import java.util.List;

public class WinningLotto {
    private final List<Integer> numbers;
    private Integer bonusNumber;
    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }
}
