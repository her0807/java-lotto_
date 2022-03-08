package domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final String INVALID_LOTTO_NUMBER_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    private static final Map<Integer, LottoNumber> CACHE;

    static {
        CACHE = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), LottoNumber::new));
    }

    private final int number;

    private LottoNumber(final int number) {
        validateRange(number);
        this.number = number;
    }

    public static List<LottoNumber> values(){
        return new ArrayList<>(CACHE.values());
    }

    public static LottoNumber of(final int number) {
        LottoNumber lottoNumber = CACHE.get(number);
        if (Objects.isNull(lottoNumber)) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
        return lottoNumber;
    }

    private static void validateRange(final int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    public int getLottoNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }
}
