package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final int LOTTO_COUNT = 6;
    private static final String ERROR_LOTTO_COUNT = LOTTO_COUNT + "개의 숫자를 입력해주세요";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";
    private static final List<Integer> lottoNumbers;

    private final List<LottoNumber> lotto;

    static {
        lottoNumbers = IntStream.range(MINIMUM_NUMBER, MAXIMUM_NUMBER + 1)
            .boxed()
            .collect(Collectors.toList());
    }

    public Lotto() {
        this(selectNumbers());
    }

    public Lotto(final List<Integer> lottoNumbers) {
        validateLotto(lottoNumbers);
        this.lotto = lottoNumbers.stream()
            .map(lottoNumber -> new LottoNumber(lottoNumber))
            .collect(Collectors.toList());
    }

    private static List<Integer> selectNumbers() {
        Collections.shuffle(lottoNumbers);

        return lottoNumbers.subList(0, LOTTO_COUNT).stream()
            .sorted()
            .collect(Collectors.toList());
    }

    private void validateLotto(final List<Integer> lotto) {
        validateLottoCount(lotto);
        validateDuplicatedNumber(lotto);
    }

    private void validateLottoCount(final List<Integer> lotto) {
        if (lotto.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(ERROR_LOTTO_COUNT);
        }
    }

    private void validateDuplicatedNumber(final List<Integer> lotto) {
        Set<Integer> distinctNumbers = new HashSet<>(lotto);
        if (distinctNumbers.size() != lotto.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lotto.stream()
            .map(LottoNumber::getLottoNumber)
            .collect(Collectors.toList()));
    }

    public int getMatchingCount(Lotto compareLotto) {
        return (int)lotto.stream()
            .filter(compareLotto::contains)
            .count();
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
    }
}
