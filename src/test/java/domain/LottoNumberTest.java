package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외 발생")
    void generateLottoNumberWhenNumberLessThanOneOrMoreThan45FailureTest(int number) {
        assertThatThrownBy(
                () -> LottoNumber.of(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 44, 45})
    @DisplayName("로또 번호가 1부터 45일때 테스트 - 성공")
    void generateLottoNumberTest(int number) {

        LottoNumber lottoNumber = LottoNumber.of(number);
        assertThat(lottoNumber.getLottoNumber()).isEqualTo(number);

    }
}