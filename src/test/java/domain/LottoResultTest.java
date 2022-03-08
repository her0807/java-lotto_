package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import controller.dto.LottoResultDto;
import service.LottoService;

public class LottoResultTest {

	@DisplayName("총 당첨금 확인")
	@Test
	void count_ranks() {
		//given
		List<Lotto> lotto = Arrays.asList(Lotto.of(new String[] {"8", "9", "11", "3", "2", "1"}),
			Lotto.of(new String[] {"8", "9", "11", "3", "2", "1"}));
		Lotto winningLotto = Lotto.of(new String[] {"6", "5", "4", "3", "2", "1"});
		LottoNumber bonusNumber = LottoNumber.of(7);
		Lottos lottos = new Lottos(lotto);
		//when
		LottoResultDto lottoResult = new LottoService().createLottoResult(lottos,
			new WinningLotto(winningLotto, bonusNumber));
		String payment = "5000";

		double profitRate = new LottoService().createProfitRate(lottoResult.toRank(), payment);

		assertThat(profitRate).isEqualTo(2.0);
	}

	@DisplayName("생성값이 null 일 경우")
	@Test
	void make_null() {
		assertThatThrownBy(() -> new LottoResult(null))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("[ERROR] ranks가 null 입니다.");
	}
}