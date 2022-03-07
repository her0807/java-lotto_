package domain.lotto;

import exception.lotto.LottoNumDuplicatedException;
import exception.lotto.LottoNumWrongSizeException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    private static final int SIZE = 6;
    private final List<LottoNumber> value;

    private Lotto(final List<LottoNumber> value) {
        validate(value);
        Collections.sort(value);
        this.value = List.copyOf(value);
    }

    public static Lotto from(final List<LottoNumber> value) {
        return new Lotto(value);
    }

    private static void validate(final List<LottoNumber> value) {
        Set<LottoNumber> setValue = new HashSet<>(value);
        if (setValue.size() != value.size()) {
            throw new LottoNumDuplicatedException(value);
        }
        if (value.size() != SIZE) {
            throw new LottoNumWrongSizeException(value.size());
        }
    }

    public int countSameNum(final WinningLotto winningLotto) {
        return (int) value.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return value.contains(lottoNumber);
    }

    public List<LottoNumber> get() {
        return List.copyOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(value, lotto1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lotto=" + value +
                '}';
    }
}