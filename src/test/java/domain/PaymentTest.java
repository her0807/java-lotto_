package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {

	@DisplayName("구입 금액이 1000원 미만")
	@Test
	void range_fail() {
		assertThatThrownBy(() -> new Payment("999"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("구입 금액이 1000원 이상 성공")
	@Test
	void range_success() {
		assertThatCode(() -> new Payment("1001"))
			.doesNotThrowAnyException();
	}

	@DisplayName("음수인 경우 실패")
	@Test
	void input_negative() {
		assertThatThrownBy(() -> new Payment("-1000"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("숫자인 경우 성공")
	@Test
	void input_success() {
		assertThatCode(() -> new Payment("1000"))
			.doesNotThrowAnyException();
	}

	@DisplayName("문자인 경우 실패")
	@Test
	void input_fail() {
		assertThatThrownBy(() -> new Payment("천만원"))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("빈문자열 실패")
	@Test
	void input_empty_fail() {
		assertThatThrownBy(() -> new Payment(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@DisplayName("구입금액 10만원 초과 실패")
	@Test
	void range_max() {
		assertThatThrownBy(() -> new Payment("110000"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("로또 생성 횟수")
	@Test
	void calculate_lotto_count() {
		assertThat(new Payment("14000").calculateLottoCount()).isEqualTo(14);
	}

	@DisplayName("수익률 계산")
	@Test
	void calculate_profit_rate() {
		Map<Rank,Integer> ranks = Rank.getMap();
		ranks.replace(Rank.FIFTH, 4);
		LottoResult lottoResult = new LottoResult(ranks);
		Payment payment = new Payment("10000");

		double profitRate = payment.calculateProfitRate(lottoResult.calculateTotalProfit());

		assertThat(profitRate).isEqualTo(2.0);
	}
}