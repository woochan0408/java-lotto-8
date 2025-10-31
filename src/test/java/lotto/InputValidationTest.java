package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("로또 구입금액 검증 테스트")
class InputValidationTest {

    @Test
    @DisplayName("음수 입력 시 IllegalArgumentException 발생")
    void testValidateNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Application.validationAmount(-1000);
        });
    }

    @Test
    @DisplayName("정상적인 금액 입력 시 예외 발생하지 않음")
    void testValidateNormalAmount() {
        assertDoesNotThrow(() -> {
            Application.validationAmount(10000);
        });
    }

    @Test
    @DisplayName("1000으로 나누어떨어지지 않는 금액 시 IllegalArgumentException 발생")
    void testValidateAmountNotDivisibleBy1000() {
        assertThrows(IllegalArgumentException.class, () -> {
            Application.validationAmount(5500);
        });
    }

}