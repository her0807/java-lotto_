package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, new Money(2_000_000_000)),
    SECOND(5, new Money(30_000_000)),
    THIRD(5, new Money(1_500_000)),
    FOURTH(4, new Money(50_000)),
    FIFTH(3, new Money(5_000)),
    NOTHING(0, new Money(0));

    private static final long SECOND_MATCH = 5;

    private final long match;
    private final Money reward;

    Rank(long match, Money reward) {
        this.match = match;
        this.reward = reward;
    }

    public static Rank from(long match, boolean hasBonus) {
        if (match == SECOND_MATCH && hasBonus) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(item -> item.getMatch() == match)
                .findAny()
                .orElse(NOTHING);
    }

    public long getMatch() {
        return this.match;
    }

    public Money getReward() {
        return this.reward;
    }

    @Override
    public String toString() {
        return String.format("Match: %d, Reward: %d", this.match, this.getReward().toLong());
    }
}