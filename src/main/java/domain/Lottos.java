package domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		Objects.requireNonNull(lottos, "[ERROR] lottos가 null 입니다.");
		this.lottos = new ArrayList<>(lottos);
	}

	public static Lottos of(List<String> manualLottos) {
		List<Lotto> lottos = manualLottos.stream()
			.map(lotto -> Lotto.of(lotto.split(",")))
			.collect(Collectors.toUnmodifiableList());

		return new Lottos(lottos);
	}

	public LottoResult createLottoResult(WinningLotto winningLotto) {
		Map<Rank, Long> ranks = LottoResult.getRankMap();

		lottos.stream()
			.map(winningLotto::calculateRank)
			.filter(rank -> !rank.isNothing())
			.collect(groupingBy(Function.identity(), counting()))
			.forEach((key, value) -> ranks.merge(key, value, (v1, v2) -> v2));
		return new LottoResult(ranks);
	}

	public List<Lotto> getLottos() {
		return new ArrayList<>(lottos);
	}

	public int getSize() {
		return lottos.size();
	}
}
