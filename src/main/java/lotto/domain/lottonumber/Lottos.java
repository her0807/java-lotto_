package lotto.domain.lottonumber;

import lotto.domain.matchkind.WinningKind;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private static final int INITIAL_MATCH_COUNT = 0;

    private final List<Lotto> values;

    public Lottos(final List<Lotto> values) {
        this.values = values;
    }

    public Map<WinningKind, Integer> match(final WinningNumbers winningNumbers) {
        final Map<WinningKind, Integer> matchResult = new EnumMap<>(WinningKind.class);
        initializeResult(matchResult);
        match(matchResult, winningNumbers);
        return matchResult;
    }

    private void initializeResult(final Map<WinningKind, Integer> result) {
        for (WinningKind matchKind : WinningKind.values()) {
            result.put(matchKind, INITIAL_MATCH_COUNT);
        }
    }

    private void match(final Map<WinningKind, Integer> matchResult, final WinningNumbers winningNumbers) {
        values.stream()
                .map(winningNumbers::getLottoMatchResult)
                .forEach(lottoMatchKind -> matchResult.put(lottoMatchKind, matchResult.get(lottoMatchKind) + 1));
    }

    public List<Lotto> getLottos() {
        return values;
    }
}
