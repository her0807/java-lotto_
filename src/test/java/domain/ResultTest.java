package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.generateStrategy.LotteryNumberMockGenerator;
import domain.lottery.Lotteries;
import domain.lottery.WinningLottery;

public class ResultTest {

	private LotteryMachine lotteryMachine;

	@BeforeEach
	void lotteryGameInit() {
		lotteryMachine = LotteryMachine.of(6000, 0, new LotteryNumberMockGenerator());
	}

	@Test
	@DisplayName("등수가 제대로 집계되는지 확인")
	void testRankingCount() {
		//given
		Lotteries lotteries = lotteryMachine.createLottery(Collections.emptyList());
		WinningLottery winningLottery = lotteryMachine.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		Result result = Result.makeResult(lotteries, winningLottery);
		//then
		final Map<Rank, Integer> rankResult = result.getRankResult();
		for (Rank rank : rankResult.keySet()) {
			rankResult.get(rank);
			assertThat(rankResult.get(rank)).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("수익률이 제대로 집계되는지 확인")
	void testRankingPercent() {
		//given
		Lotteries lotteries = lotteryMachine.createLottery(Collections.emptyList());
		WinningLottery winningLottery = lotteryMachine.createWinningLottery(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
		//when
		Result result = Result.makeResult(lotteries, winningLottery);
		//then
		assertThat(result.getReturnRate()).isEqualTo((double)2031555000 / (6 * 1000));
	}
}
