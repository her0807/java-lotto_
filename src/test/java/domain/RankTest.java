package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RankTest {

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        int[] numbers = {1, 2, 3, 10, 11, 12};
        int bonus = 45;
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = Rank.calculateRank(lotto, winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("4개 일치시 4등이다.")
    void lotto_calculateRightFourthRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 6, 8)));

        int[] numbers = {1, 2, 3, 4, 5, 12};
        int bonus = 6;
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = Rank.calculateRank(lotto, winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 2등이다.")
    void lotto_calculateRightSecondRank() {
        Lotto lotto = new Lotto(new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));

        int[] numbers = {1, 2, 3, 4, 5, 12};
        int bonus = 6;
        WinningNumbers winningLotto = new WinningNumbers(numbers, bonus);

        Rank winnerPrice = Rank.calculateRank(lotto, winningLotto);
        assertThat(winnerPrice).isEqualTo(Rank.SECOND);
    }
}
