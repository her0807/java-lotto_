package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LotteryTicketsTest {
    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        List<Integer> input = IntStream.range(7, 13)
                .boxed()
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = LotteryTicket.from(input);
        List<LotteryTicket> lotteryTicketList = List.of(lotteryTicket);
        LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(1, 0, 1);
        LotteryTickets lotteryTickets = new LotteryTickets(lotteryTicketList, lotteryPurchaseCount);
        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("6개의 숫자가 아닌 리스트를 입력 받으면 예외를 throw 한다.")
    void validateAdd() {
        List<Integer> input = IntStream.range(6, 13)
                .boxed()
                .collect(Collectors.toList());
        assertThatThrownBy(() -> {
            List<LotteryTicket> tickets = List.of(LotteryTicket.from(input));
            LotteryPurchaseCount lotteryPurchaseCount = new LotteryPurchaseCount(1, 1, 1);
            LotteryTickets lotteryTickets = new LotteryTickets(tickets, lotteryPurchaseCount);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 여섯개를 입력해야합니다.");
    }

    @Test
    @DisplayName("총 구매 개수와 입력 받은 로또 티켓 개수가 일치하지 않으면, 에러를 발생시킨다.")
    void validateCorrectSize() {
        LotteryPurchaseCount count = new LotteryPurchaseCount(3, 5, 8);
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<LotteryTicket> lotteryTicket = LotteryTicket.createLotteryTickets(List.of(input));
        assertThatThrownBy(() -> {
            LotteryTickets value = new LotteryTickets(lotteryTicket, count);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("");


    }
}