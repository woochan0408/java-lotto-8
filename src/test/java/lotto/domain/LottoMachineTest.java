package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void purchaseLottosByInvalidUnit() {
        LottoMachine lottoMachine = new LottoMachine();
        assertThatThrownBy(() -> lottoMachine.purchaseLottos(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 0이하면 예외가 발생한다.")
    @Test
    void purchaseLottosByZeroOrLess() {
        LottoMachine lottoMachine = new LottoMachine();
        assertThatThrownBy(() -> lottoMachine.purchaseLottos(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> lottoMachine.purchaseLottos(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액만큼 로또를 생성한다.")
    @Test
    void purchaseLottos() {
        LottoMachine lottoMachine = new LottoMachine() {
            @Override
            protected List<Integer> generateLottoNumbers() {
                return List.of(1, 2, 3, 4, 5, 6);
            }
        };
        List<Lotto> lottos = lottoMachine.purchaseLottos(3000);
        assertThat(lottos).hasSize(3);
    }
}
