package lotto.utils;

import java.util.Random;

import lotto.domain.LottoNumber;

public class RandomLottoNumberGenerator {
    private static final Random random;
    private static final int MAX_NUMBER = 45;

    private RandomLottoNumberGenerator() {
    }

    static {
        random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    public static LottoNumber generate() {
        return new LottoNumber(random.nextInt(MAX_NUMBER) + 1);
    }
}
