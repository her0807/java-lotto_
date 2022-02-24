package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	@DisplayName("랭크 리스트 생성")
	@Test
	void calculate_rank_success() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"}),
			Lotto.of(new String[]{"11", "5", "4", "3", "2", "1"}));
		Lotto winningLotto = Lotto.of(new String[]{"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = new LottoNumber("7");
		Lottos lottos = new Lottos(lotto);

		//when
		List<Rank> ranks = lottos.calculateRank(new WinningLotto(winningLotto, bonusNumber));

		//then
		assertThat(ranks).contains(Rank.FIRST, Rank.THIRD);
	}
}
