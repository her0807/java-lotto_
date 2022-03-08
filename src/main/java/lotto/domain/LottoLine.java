package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.generator.NumberGenerator;

public class LottoLine {

    public static final int PRICE = 1000;
    private static final int NUMBER_COUNT = 6;

    private final List<LottoNumber> numbers;

    public LottoLine(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static LottoLine createLine(NumberGenerator generator) {
        return new LottoLine(
            generator.generate(NUMBER_COUNT)
                .stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList())
        );
    }

    private void validate(List<LottoNumber> numbers) {
        validateLength(numbers);
        validateDistinct(numbers);
    }

    private void validateLength(List<LottoNumber> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException(String.format("로또 번호의 개수는 %d 이어야 합니다.", NUMBER_COUNT));
        }
    }

    private void validateDistinct(List<LottoNumber> numbers) {
        if (!isDistinct(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private boolean isDistinct(List<LottoNumber> numbers) {
        return new HashSet<>(numbers).size() == NUMBER_COUNT;
    }

    public int countMatchingNumbers(LottoLine other) {
        return Math.toIntExact(
            this.numbers.stream()
            .filter(other::contains)
            .count()
        );
    }

    public boolean contains(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
