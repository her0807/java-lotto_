package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    @DisplayName("2등 당첨 번호를 비교한다")
    void matchNumber() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = Lottos.generateManual(Collections.singletonList(lotto));

        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 45));
        LottoNumber bonusNumber = new LottoNumber(6);

        LottoResult lottoResult = LottoResult.create(lottos, winningNumbers, bonusNumber);

        assertThat(lottoResult.getRankCount(Rank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("3등 당첨 여부를 확인한다")
    void rankFirstTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lottos lottos = Lottos.generateManual(Collections.singletonList(lotto));

        WinningNumbers winningNumbers = new WinningNumbers(Arrays.asList(1, 2, 3, 4, 5, 45));
        LottoNumber bonusNumber = new LottoNumber(44);

        LottoResult lottoResult = LottoResult.create(lottos, winningNumbers, bonusNumber);

        assertThat(lottoResult.getRankCount(Rank.THIRD)).isEqualTo(1);;
    }
}
