package domain;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumberFactory;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.CustomLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static domain.Lotto.LottoNumberFactory.LOTTO_NUMBER_BOUNDARY_CACHE;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    @Test
    @DisplayName("AutoLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto() {
        Lotto actual = new AutoLottoGenerator().generateLotto(LOTTO_NUMBER_BOUNDARY_CACHE);
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("WinningLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateWinningLotto() {
        List<String> lottoNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        Lotto actual = new CustomLottoGenerator().generateLotto(LottoNumberFactory.from(lottoNumbers));
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

}