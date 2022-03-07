package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_SIZE = 6;
    private static final String ERROR_DUPLICATE_NUMBER = "로또 번호는 중복되지 않은 6자리 숫자여야 합니다.";

    private final Set<Number> lottoNumbers;

    public Lotto(Set<Integer> numbers) {
        checkRightSize(numbers);
        lottoNumbers = new HashSet<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new Number(number));
        }
    }

    private void checkRightSize(Set<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    public boolean hasMatchedNumber(Number number) {
        return lottoNumbers.contains(number);
    }

    public Set<Number> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
