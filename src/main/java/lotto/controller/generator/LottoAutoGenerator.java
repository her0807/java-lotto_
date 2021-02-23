package lotto.controller.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoAutoGenerator implements LottoGenerator {

    private static final List<Integer> candidateNumbers = new ArrayList<>();

    public static final int LOTTO_START_NUMBER = 1;
    public static final int LOTTO_END_NUMBER = 45;
    public static final int LOTTO_POSSESSION_NUMBER = 6;

    static {
        for (int number = LOTTO_START_NUMBER; number <= LOTTO_END_NUMBER; number++) {
            candidateNumbers.add(number);
        }
    }

    @Override
    public List<Integer> generateNumbers() {
        Collections.shuffle(candidateNumbers);
        Set<Integer> lottoNumbers =
            new TreeSet<>(candidateNumbers.subList(0, LOTTO_POSSESSION_NUMBER));
        return Collections.synchronizedList(new ArrayList<>(lottoNumbers));
    }
}