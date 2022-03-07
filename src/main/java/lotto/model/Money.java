package lotto.model;

public class Money {
    private static final String ERROR_NEGATIVE = "[ERROR] 구매 금액은 0원보다 커야 합니다";
    private static final String ERROR_TYPE = "[ERROR] 구매 금액은 숫자로만 입력하세요";
    private static final String ERROR_UNIT = "[ERROR] 구매 금액은 1000원 단위로 입력하세요";
    private static final int PRICE_LOTTO = 1000;

    private final int amount;

    private Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        checkNegative(amount);
        checkUnit(amount);
    }

    private void checkNegative(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    private static void checkUnit(int amount) {
        if (amount % PRICE_LOTTO != 0) {
            throw new IllegalArgumentException(ERROR_UNIT);
        }
    }

    public static Money from(String rawAmount) {
        try {
            return new Money(Integer.parseInt(rawAmount));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public int countAvailableLotto() {
        return this.amount / PRICE_LOTTO;
    }

    public double rate(long numerator) {
        return numerator / (double) this.amount;
    }
}
