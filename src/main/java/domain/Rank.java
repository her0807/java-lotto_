package domain;

import java.util.Arrays;
import java.util.EnumMap;

public enum Rank {
	NOTHING(0, 0, false),
	FIFTH(5000, 3, false),
	FOURTH(50000, 4, false),
	THIRD(150000, 5, false),
	SECOND(30000000, 5, true),
	FIRST(2000000000, 6, false);

	private final int money;
	private final int match;
	private final boolean bonus;

	Rank(int money, int match, boolean bonus) {
		this.money = money;
		this.match = match;
		this.bonus = bonus;
	}

	public static Rank of(int target, boolean bonus) {
		return Arrays.stream(Rank.values())
			.filter(rank -> rank.match == target)
			.filter(rank -> rank.bonus == bonus)
			.findFirst()
			.orElse(NOTHING);
	}

	public static EnumMap<Rank, Long> getMap() {
		EnumMap<Rank, Long> ranks = new EnumMap<Rank, Long>(Rank.class);

		Arrays.stream(values())
			.filter(rank -> !rank.equals(NOTHING))
			.forEach(rank -> ranks.put(rank, 0L));
		return ranks;
	}

	public boolean isNothing() {
		return this.equals(NOTHING);
	}

	public int calculateMoney(int count) {
		return this.money * count;
	}

	public boolean isBonus() {
		return this.bonus;
	}

	public int getMoney() {
		return money;
	}

	public int getMatch() {
		return match;
	}

	public Rank getRank() {
		return this;
	}
}
