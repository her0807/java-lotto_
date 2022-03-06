package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.vo.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.constants.Lotto.LAST_LOTTO_NUMBER;

public class LottoRandomGenerator {
    private static final int BONUS_NUMBER_INDEX = 6;

    private final List<LottoNumber> basicNumbers;

    public LottoRandomGenerator() {
        basicNumbers = IntStream.range(0, LAST_LOTTO_NUMBER)
                .mapToObj(index -> LottoNumber.from(index + 1))
                .collect(Collectors.toList());
    }

    public List<Lotto> generateLottosExceptDefaultLottos(final int numberOfGenerating, final List<Lotto> generated) {
        while (generated.size() < numberOfGenerating) {
            generated.add(generateLotto());
        }
        return Collections.unmodifiableList(generated);
    }

    private Lotto generateLotto() {
        Collections.shuffle(basicNumbers);
        final Set<LottoNumber> generatedNumbers = basicNumbers.stream()
                .limit(BONUS_NUMBER_INDEX)
                .collect(Collectors.toUnmodifiableSet());
        return new Lotto(generatedNumbers);
    }
}
