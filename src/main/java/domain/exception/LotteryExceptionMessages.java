package domain.exception;

public enum LotteryExceptionMessages {
	INVALID_SIZE_EXCEPTION("로또 번호는 6개여야 합니다."),
	INVALID_RANGE_EXCEPTION("로또의 번호는 1~45 사이여야 합니다"),
	DUPLICATE_NUMBER_EXCEPTION("당첨번호와 보너스볼에 중복된 번호가 있으면 안됩니다.");

	private String message;

	LotteryExceptionMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
