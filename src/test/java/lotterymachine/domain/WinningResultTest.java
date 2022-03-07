package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningResultTest {
    @Test
    @DisplayName("당첨 번호와 로또 티켓을 입력 받아 결과를 저장한다.")
    void getResult() {
        List<Integer> lotteryNumbers = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(LotteryTicket.from(lotteryNumbers));
        LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(1, 0, 1);
        LotteryTickets lotteryTickets = new LotteryTickets(tickets, lotteryPurchaseCount);
        LotteryNumber bonusNumber = LotteryNumber.from(7);
        WinningLottery winningLottery = new WinningLottery(LotteryTicket.from(lotteryNumbers), bonusNumber);
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        Map<WinningLotteryRank, Integer> value = winningResult.getResult();
        assertThat(value.get(WinningLotteryRank.SIX)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 당첨 금액을 조회한다.")
    void findTotalProfit() {
        List<Integer> lotteryNumbers = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toList());
        List<LotteryTicket> tickets = List.of(LotteryTicket.from(lotteryNumbers));
        LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(1, 0, 1);
        LotteryTickets lotteryTickets = new LotteryTickets(tickets, lotteryPurchaseCount);
        LotteryNumber bonusNumber = LotteryNumber.from(7);
        WinningLottery winningLottery = new WinningLottery(LotteryTicket.from(lotteryNumbers), bonusNumber);
        WinningResult winningResult = new WinningResult(lotteryTickets, winningLottery);
        assertThat(winningResult.findTotalProfit()).isEqualTo(2000000000);
    }
}