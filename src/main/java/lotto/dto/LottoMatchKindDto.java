package lotto.dto;

import lotto.domain.LottoMatchKind;

public class LottoMatchKindDto {
    private final int matchedCount;
    private final boolean matchedBonus;
    private final long winningAmount;
    private final int winningCount;

    public LottoMatchKindDto(final LottoMatchKind lottoMatchKind, final int winningCount) {
        this.matchedCount = lottoMatchKind.getMatchCount();
        this.matchedBonus = lottoMatchKind.hasSameNumberWithBonus();
        this.winningAmount = lottoMatchKind.getWinningAmount();
        this.winningCount = winningCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public boolean hasMatchedBonus() {
        return matchedBonus;
    }

    public long getWinningAmount() {
        return winningAmount;
    }

    public int getWinningCount() {
        return winningCount;
    }
}
