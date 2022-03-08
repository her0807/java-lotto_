package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoNumberGenerator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @Test
    @DisplayName("구입 금액에 따른 개수만큼 로또 생성")
    public void generateLottosTest() {
        int count = 1;
        Lottos lottos = Lottos.generateLottos(List.of(new AutoLottoGenerator()));
        assertThat(lottos.size()).isEqualTo(count);
    }

    @Test
    @DisplayName("당첨 번호 통계 테스트")
    public void checkWinningStatisticsTest() {
        Lotto firstLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 4, 5, 6));
        Lotto fifthLotto = new Lotto(LottoNumberGenerator.of(1, 2, 3, 10, 11, 12));
        Lotto missLotto = new Lotto(LottoNumberGenerator.of(13, 14, 15, 16, 17, 18));
        LottoNumber bonusBall = LottoNumber.of(7);

        WinningLotto winningNumber = new WinningLotto(firstLotto, bonusBall);

        Lottos lottos = new Lottos(Arrays.asList(firstLotto, fifthLotto, missLotto));
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber);

        assertThat(winningStatistics.getStatistics().get(Rank.FIRST)).isEqualTo(1);
        assertThat(winningStatistics.getStatistics().get(Rank.SECOND)).isEqualTo(0);
        assertThat(winningStatistics.getStatistics().get(Rank.FIFTH)).isEqualTo(1);

    }

    @Test
    @DisplayName("수동 로또와 자동 로또가 합쳐서 하나의 로또로 되는지 테스트")
    void addManualAndAutoLottosTest() {
        String[] numbers = new String[]{"1", "3", "5", "7", "9", "11"};
        Lottos lottos = Lottos.generateLottos(List.of(new ManualLottoGenerator(numbers), new AutoLottoGenerator()));

        assertThat(lottos.size()).isEqualTo(2);
    }
}