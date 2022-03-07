package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.constant.ErrorMessage.ERROR_STRING_CONVERTER_NOT_NUMBER;
import static lotto.constant.ErrorMessage.ERROR_STRING_CONVERTER_NULL_OR_BLANK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringToIntConverterTest {

    @Test
    @DisplayName("입력값이 숫자가 아닌 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsNotNumber() {
        String input = "notNumber";
        assertThatThrownBy(() -> StringConverter.toInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_STRING_CONVERTER_NOT_NUMBER.message());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("입력값이 empty, blank일 경우 예외를 발생시킨다")
    void throwExceptionWhenInputIsEmptyOrBlank(String input) {
        assertThatThrownBy(() -> StringConverter.toInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_STRING_CONVERTER_NULL_OR_BLANK.message());
    }
}
