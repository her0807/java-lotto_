package domain.lottery;

import static domain.exception.LotteryExceptionMessages.*;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public final class Lottery {

	public static final int LOTTERY_SIZE = 6;

	private final Set<LotteryNumber> numbers;

	private Lottery(final Set<LotteryNumber> numbers) {
		final Set<LotteryNumber> tempNumbers = deepCopyOf(numbers);
		validateNumbers(tempNumbers);
		this.numbers = tempNumbers;
	}

	private Set<LotteryNumber> deepCopyOf(final Set<LotteryNumber> numbers) {
		return new TreeSet<>(numbers);
	}

	public static Lottery from(final Set<LotteryNumber> numbers) {
		return new Lottery(numbers);
	}

	private void validateNumbers(final Set<LotteryNumber> numbers) {
		validateSize(numbers.size());
	}

	private void validateSize(final int size) {
		if (size != LOTTERY_SIZE) {
			throw new IllegalArgumentException(INVALID_SIZE_EXCEPTION.getMessage());
		}
	}

	public int countSameNumber(final Lottery lottery) {
		return (int)lottery.numbers.stream()
			.filter(this::contains)
			.count();
	}

	public boolean contains(final LotteryNumber number) {
		return numbers.contains(number);
	}

	public Set<LotteryNumber> getNumbers() {
		return Collections.unmodifiableSet(numbers);
	}

	public boolean isDuplicated(LotteryNumber number) {
		return this.numbers.stream()
			.anyMatch(lotteryNumber -> lotteryNumber.equals(number));
	}
}
