package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

    private final Map<Rank, Integer> results = new LinkedHashMap();

    public void add(Rank rank) {
        int previousCount = results.getOrDefault(rank, 0);
        results.put(rank, previousCount + 1);
    }

    public Map<Rank, Integer> get() {
        return results;
    }

    public int getRankCount(Rank rank) {
        return results.getOrDefault(rank, 0);
    }

    public float getProfit(int money) {
        return (float) getReward() / (float) money;
    }

    private long getReward() {
        return results.entrySet()
            .stream()
            .mapToLong(entry -> (long) entry.getKey().getPrizeMoney() * entry.getValue())
            .sum();
    }
}
