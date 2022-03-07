package lotto.model;

public class PurchaseCount {
    private static final String ERROR_COUNT_OVER = "[ERROR] 구매할 수 있는 수량을 초과했습니다";
    private static final String ERROR_NEGATIVE = "[ERROR] 구매 수량은 0보다 작을 수 없습니다";
    public static final String ERROR_TYPE = "[ERROR] 로또 구매 수량은 숫자로만 입력해주세요";

    private final int auto;
    private final int manual;

    private PurchaseCount(int total, int manual) {
        validate(total, manual);
        this.auto = total - manual;
        this.manual = manual;
    }

    private void validate(int total, int manual) {
        checkCount(total, manual);
        checkNegative(manual);
    }

    private void checkCount(int total, int manual) {
        if (total < manual) {
            throw new IllegalArgumentException(ERROR_COUNT_OVER);
        }
    }

    private void checkNegative(int manual) {
        if (manual < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    public static PurchaseCount of(Money money, String rawManualCount) {
        try {
            return new PurchaseCount(
                    money.countAvailableLotto(), Integer.parseInt(rawManualCount));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public boolean isTotal(int count) {
        return (this.auto + this.manual) == count;
    }

    public boolean isManual(int count) {
        return this.manual == count;
    }

    public int getAuto() {
        return auto;
    }

    public int getManual() {
        return manual;
    }
}
