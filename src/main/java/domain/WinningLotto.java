package domain;

public class WinningLotto {
    static final String ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 로또 번호와 중복될 수 없습니다.";

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicateBonusNumber(lotto, bonusNumber);

        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "lotto=" + lotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
