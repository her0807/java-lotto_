package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.LottoNumberGenerateStrategy;
import domain.strategy.StubRandomLottoNumberGenerator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {
    private LottoGame lottoGame;
    private LottoNumberGenerateStrategy lottoNumberGenerateStrategy;

    @BeforeEach
    void setUp() {
        LottoQuantity totalLottoQuantity = LottoQuantity.from(5);
        LottoQuantity manualLottoQuantity = LottoQuantity.from(0);

        lottoGame = new LottoGame(totalLottoQuantity, manualLottoQuantity);
    }

    @BeforeEach
    void setUpStrategy() {
        lottoNumberGenerateStrategy = StubRandomLottoNumberGenerator.fromRawValues(
                List.of(
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12),
                        Set.of(13, 14, 15, 16, 17, 18)
                )
        );
    }

    @Test
    @DisplayName("LottoGame 생성자에 LottoQuantity 와 Lottos 를 넣으면 객체가 생성된다.")
    void createLottoGameWithInputMoney() {
        // given
        LottoQuantity totalLottoQuantity = LottoQuantity.from(10);
        LottoQuantity manualLottoQuantity = LottoQuantity.from(5);
        Lottos lottos = Lottos.fromRawValues(
                List.of(
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12)
                )
        );

        // when
        LottoGame lottoGame = new LottoGame(totalLottoQuantity, manualLottoQuantity);

        // then
        assertThat(lottoGame).isNotNull();
    }

    @Test
    @DisplayName("createManualLottos 에 List<Set<Integer>> 를 전달하면 Lottos 로 매핑하여 반환한다.")
    void createManualLottos() {
        // given & when
        List<Set<Integer>> rawLottosValue = List.of(
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(7, 8, 9, 10, 11, 12)
        );
        Lottos expected = Lottos.fromRawValues(rawLottosValue);

        // when
        Lottos actual = lottoGame.createManualLottos(rawLottosValue);

        // then
        assertThat(actual.getLottos()).isEqualTo(expected.getLottos());
    }

    @Test
    @DisplayName("createAutoLottos 에 LottoQuantity 를 전달하면 생성된 총 Lottos 를 반환한다.")
    void createAutoLottos() {
        // given & when
        Lottos expected = Lottos.fromRawValues(
                List.of(
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12),
                        Set.of(13, 14, 15, 16, 17, 18),
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12)
                )
        );
        Lottos actual = lottoGame.createAutoLottos(lottoNumberGenerateStrategy);

        // then
        assertThat(actual.getLottos()).isEqualTo(expected.getLottos());
    }

    @Test
    @DisplayName("createWinningResult 에 Lottos 와 WinningLotto 를 전달하면 WinningResult 를 반환한다.")
    void createWinningResult() {
        // given
        Lottos totalLottos = Lottos.fromRawValues(
                List.of(
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(1, 2, 3, 4, 5, 6),
                        Set.of(7, 8, 9, 10, 11, 12),
                        Set.of(7, 8, 9, 10, 11, 12),
                        Set.of(13, 14, 15, 16, 17, 18)
                )
        );
        WinningLotto winningLotto = new WinningLotto(
                Lotto.fromRawValues(Set.of(1, 2, 3, 4, 5, 7)),
                LottoNumber.from(6)
        );

        // when
        lottoGame.createAutoLottos(lottoNumberGenerateStrategy);
        WinningResult actual = lottoGame.createWinningResult(winningLotto);
        WinningResult expected = new WinningResult(totalLottos, winningLotto);

        // then
        assertThat(actual.getWinningResult()).isEqualTo(expected.getWinningResult());
    }
}
