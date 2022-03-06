package lotto.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;
import lotto.domain.LottoPrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResponseWinningResultsDtoTest {

    @DisplayName("당첨 결과를 가지는 dto를 생성한다.")
    @Test
    void response_winning_results_dto_test() {
        // given
        Map<LottoPrize, Integer> map = new EnumMap<>(LottoPrize.class);

        // when
        ResponseWinningResultsDto dto = new ResponseWinningResultsDto(map);

        // then
        assertThat(dto.getResults()).isEqualTo(map);
    }
}
