package lotto.domain.lottonumber;

import lotto.domain.matchkind.WinningKind;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static lotto.domain.matchkind.WinningKind.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {
    @Test
    @DisplayName("당첨 번호와 보너스 번호에 중복이 있으면 예외를 발생시킨다.")
    void create_exceptionByDuplicationOfTargetNumbersAndBonusNumber_Test() {
        //given
        final List<Integer> winningLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 1;
        final String expectedExceptionMessage = "당첨 번호와 보너스 번호에 중복이 있으면 안됩니다.";
        //when then
        assertThatThrownBy(() -> new WinningNumbers(winningLottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @ParameterizedTest
    @DisplayName("로또 하나를 받아 당첨 번호와 보너스 번호를 비교해 당첨 종류를 반환한다.")
    @MethodSource("provideLottoNumbersAndMatchKind")
    void getLottoMatchResult_Test(final List<Integer> otherLottoNumbers, final WinningKind expected) {
        //given
        final List<Integer> winningLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        final int bonusNumber = 45;
        final WinningNumbers winningNumbers = new WinningNumbers(winningLottoNumbers, bonusNumber);
        final Lotto lotto = new Lotto(otherLottoNumbers);
        //when
        final WinningKind actual = winningNumbers.getLottoMatchResult(lotto);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLottoNumbersAndMatchKind() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), SIX),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 45), FIVE_BONUS),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 7), FIVE),
                Arguments.of(Arrays.asList(3, 4, 5, 6, 7, 45), FOUR),
                Arguments.of(Arrays.asList(3, 4, 5, 6, 7, 8), FOUR),
                Arguments.of(Arrays.asList(4, 5, 6, 7, 8, 45), THREE),
                Arguments.of(Arrays.asList(4, 5, 6, 7, 8, 9), THREE),
                Arguments.of(Arrays.asList(5, 6, 7, 8, 9, 45), LOWER_THAN_THREE),
                Arguments.of(Arrays.asList(7, 8, 9, 10, 11, 12), LOWER_THAN_THREE),
                Arguments.of(Arrays.asList(8, 9, 10, 11, 12, 13), LOWER_THAN_THREE),
                Arguments.of(Arrays.asList(9, 10, 11, 12, 13, 14), LOWER_THAN_THREE),
                Arguments.of(Arrays.asList(10, 11, 12, 13, 14, 15), LOWER_THAN_THREE)
        );
    }
}
