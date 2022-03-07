package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoMachineTest {

    private LottoMachine lottoMachine;
    private Lottos lottos;

    @BeforeEach
    void init() {
        lottoMachine = new LottoMachine();
        lottos = new Lottos();
    }

    @DisplayName("1000원 이하의 입력 들어오면 예외가 발생한다.")
    @ParameterizedTest(name = "{index} {displayName} money={0}")
    @ValueSource(strings = {"-10000", "-50000", "700", "999"})
    void checkUnderMinimumMoneyInput_throwIllegalException(final int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가격은 1000원 이상만 가능합니다.");
    }

    @DisplayName("로또는 1개에 1000원이다.")
    @ParameterizedTest(name = "{index} {displayName} {0}원으로 구매한 로또 수 : {1}")
    @CsvSource(value = {"20000, 20", "18000, 18", "1000, 1"})
    void purchaseLottos(final int inputMoney, final int expected) {
        Money money = new Money(inputMoney);
        lottoMachine.purchaseAutomaticLottos(lottos, money);

        assertThat(lottos.getLottos().size()).isEqualTo(expected);
    }
}
