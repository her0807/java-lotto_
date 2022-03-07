package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.RankBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTO_JOIN_DELIMITER = ", ";

    private OutputView() {
    }

    public static void printLottos(final int manualLottoCount, final int autoLottoCount, final List<Lotto> lottos) {
        printLottoCount(manualLottoCount, autoLottoCount);

        for (Lotto lotto : lottos) {
            System.out.println(makeLottoString(lotto));
        }
        System.out.println();
    }

    private static void printLottoCount(final int manualLottoCount, final int autoLottoCount) {
        System.out.println("수동으로 " + manualLottoCount + "개, 자동으로 " + autoLottoCount + "개를 구매했습니다.");
    }

    private static String makeLottoString(final Lotto lotto) {
        final String result = lotto.getIntValues().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_JOIN_DELIMITER));
        return "[" + result + "]";
    }

    public static void printResult(final RankBoard rankBoard, final double profitRatio) {
        printResultTitle();
        printRankCounter(rankBoard);
        printProfitRatio(profitRatio);
    }

    private static void printResultTitle() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printRankCounter(final RankBoard rankBoard) {
        final List<Rank> ranks = new ArrayList<>(Arrays.asList(Rank.values()));
        ranks.sort(Comparator.comparing(Rank::prize));
        for (Rank rank : ranks) {
            System.out.print(makeRankString(rank, rankBoard.getCount(rank)));
        }
    }

    private static String makeRankString(final Rank rank, final int count) {
        if (rank == Rank.MISS) {
            return "";
        }
        if (rank == Rank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원) - %d개\n", rank.matched(), rank.prize(), count);
        }
        return String.format("%d개 일치 (%d원) - %d개\n", rank.matched(), rank.prize(), count);
    }

    private static void printProfitRatio(final double profitRatio) {
        System.out.println("총 수익률은 " + profitRatio + "입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}
