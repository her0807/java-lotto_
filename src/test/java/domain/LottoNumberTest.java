package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("LottoNumber 테스트")
public class LottoNumberTest {
    @DisplayName("생성자에 1 ~ 45 의 숫자를 전달하면 객체가 생성된다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {1, 45})
    void createLottoNumber(int number) {
        // given & when
        LottoNumber lottoNumber = LottoNumber.from(number);

        // then
        assertThat(lottoNumber.toInt()).isEqualTo(number);
    }

    @DisplayName("1 ~ 45 범위 외 숫자가 전달되면, IAE 가 발생한다.")
    @ParameterizedTest(name = "{0} 전달")
    @ValueSource(ints = {-1, 0, 46})
    void createLottoNumberOutOfRangeShouldFail(int number) {
        assertThatThrownBy(() -> LottoNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LottoNumber.ERROR_MESSAGE_FOR_OUT_OF_RANGE_NUMBER);
    }

    @Test
    @DisplayName("같은 숫자가 전달된 LottoNumber 끼리는 동등성을 가져야한다.")
    void lottoNumberEqualityTest() {
        // given & when
        LottoNumber lottoNumber1 = LottoNumber.from(1);
        LottoNumber lottoNumber2 = LottoNumber.from(1);

        // then
        assertThat(lottoNumber1).isEqualTo(lottoNumber2);
    }
}
