package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.Validator;

public class Lotto {

    private static final String INPUT_NUMBER_DELIMITER = ",";
    private static final int INPUT_NUMBER_SPLIT_OPTION = -1;
    private static final String ERROR_LOTTO_SIZE_MESSAGE = "입력받은 로또 개수가 6개가 아닙니다.";
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(final String inputLotto) {
        Validator.checkNullOrEmpty(inputLotto);
        List<String> splitNumbers = splitInput(inputLotto);
        validateLottoSize(splitNumbers);
        Validator.checkDuplication(splitNumbers);
        this.lotto = splitNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

    private void validateLottoSize(List<String> splitNumbers) {
        if (splitNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE_MESSAGE);
        }
    }

    public Lotto(final List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    private List<String> splitInput(final String inputNumbers) {
        return Arrays.stream(inputNumbers.split(INPUT_NUMBER_DELIMITER, INPUT_NUMBER_SPLIT_OPTION))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean isContainNumber(final LottoNumber lottoNumber) {
        return this.lotto.contains(lottoNumber);
    }

    public int compare(final Lotto lotto) {
        return (int) this.lotto.stream()
                .filter(lotto::isContainNumber)
                .count();
    }

    public List<LottoNumber> getLotto() {
        return this.lotto;
    }
}
