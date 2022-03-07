package domain;

import utils.Validator;

public class Money {

    private static final int LOTTO_PRICE = 1_000;
    private static final String ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE = "원 미만은 입력할 수 없습니다.";
    private static final String ERROR_UPPER_THAN_MONEY_MESSAGE = "수동으로 구매하려는 로또 수가 구입금액보다 많습니다.";

    private final int money;
    private final int count;

    public Money(final String input) {
        validateInputMoney(input);
        this.money = Integer.parseInt(input);
        this.count = this.money / LOTTO_PRICE;
        validateMoneyRange(this.money);
    }

    public int getCount() {
        return count;
    }

    private void validateInputMoney(final String input) {
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
    }

    private void validateMoneyRange(final int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(LOTTO_PRICE + ERROR_LOWER_THAN_LOTTO_PRICE_MESSAGE);
        }
    }

    public void checkOverPrice(final int count) {
        if (count > this.count) {
            throw new IllegalArgumentException(ERROR_UPPER_THAN_MONEY_MESSAGE);
        }
    }

    public double calculateProfit(final Prize totalWinPrize) {
        return totalWinPrize.calculateProfit(this.count * LOTTO_PRICE);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Money money1 = (Money) object;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }
}
