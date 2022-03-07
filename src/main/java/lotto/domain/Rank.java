package lotto.domain;

import java.util.Arrays;

public enum Rank {

    MATCH_SIX_NUMBERS(6, 2_000_000_000, "6개 일치"),
    MATCH_FIVE_AND_BONUS_NUMBERS(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    MATCH_FIVE_NUMBERS(5, 1_500_000, "5개 일치"),
    MATCH_FOUR_NUMBERS(4, 50_000, "4개 일치"),
    MATCH_THREE_NUMBERS(3, 5_000, "3개 일치"),
    MATCH_MISS(0, 0, "3개 미만 일치");

    private final int matchCount;
    private final int reward;
    private final String matchStatus;

    Rank(final int matchCount, final int reward, final String matchStatus) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.matchStatus = matchStatus;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public static Rank getMatchResult(int total, boolean isMatchWithBonusNumber) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == total)
                .filter(rank -> !rank.equals(MATCH_FIVE_AND_BONUS_NUMBERS) || isMatchWithBonusNumber)
                .findFirst()
                .orElse(MATCH_MISS);
    }

    public double calculateTotalReward(Integer count) {
        return reward * count;
    }
}

