package lotterymachine.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LotteryTicket {
    public static final int TICKET_SIZE = 6;
    public static final int PER_PRICE = 1000;
    private static final String NOT_CORRECT_TICKET_SIZE = "로또 숫자는 여섯개를 입력해야합니다.";
    private static final String DUPLICATION_INPUT_NUMBERS = "중복된 숫자를 입력 받았습니다.";

    private final List<LotteryNumber> numbers;

    public LotteryTicket(final List<LotteryNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public static LotteryTicket from(List<Integer> value) {
        List<LotteryNumber> numbers = value.stream()
                .map(LotteryNumber::from)
                .collect(Collectors.toList());
        return new LotteryTicket(numbers);
    }

    public static List<LotteryTicket> createLotteryTickets(List<List<Integer>> value) {
        List<LotteryTicket> lotteryTickets = new ArrayList<>();
        for (List<Integer> numbers : value) {
            List<LotteryNumber> lotteryNumbers = LotteryNumber.from(numbers);
            lotteryTickets.add(new LotteryTicket(lotteryNumbers));
        }
        return lotteryTickets;
    }

    private static boolean isLotteryTicketSize(int size) {
        return size == TICKET_SIZE;
    }

    private void validateNumbers(List<LotteryNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LotteryNumber> lotteryNumbers) {
        if (!isLotteryTicketSize(lotteryNumbers.size())) {
            throw new IllegalArgumentException(NOT_CORRECT_TICKET_SIZE);
        }
    }

    private void validateDuplication(List<LotteryNumber> lotteryNumbers) {
        int count = (int) lotteryNumbers.stream()
                .distinct()
                .count();
        if (count != lotteryNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATION_INPUT_NUMBERS);
        }
    }

    public int countMatchingNumbers(LotteryTicket lotteryTicket) {
        return (int) this.numbers.stream()
                .filter(lotteryTicket::containsNumber)
                .count();
    }

    public boolean containsNumber(LotteryNumber lotteryNumber) {
        return this.numbers.contains(lotteryNumber);
    }

    public List<Integer> getNumbers() {
        Collections.sort(this.numbers);
        return numbers.stream()
                .map(LotteryNumber::getNumber)
                .collect(Collectors.toUnmodifiableList());
    }
}