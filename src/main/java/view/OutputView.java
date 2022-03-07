package view;

import domain.Lotto;
import domain.RankPrize;

import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

public class OutputView {

    private static final String INPUT_MONEY_INSTRUCTION = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String INPUT_WIN_LOTTO_INSTRUCTION = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_INSTRUCTION = "보너스 볼을 입력해 주세요.";
    private static final String WIN_STATISTICS_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String SECOND_RANK_CORRECT_MESSAGE = "개 일치, 보너스 볼 일치(";
    private static final String RANK_PRIZE_MESSAGE = "원)- ";
    private static final String RANK_COUNT_MESSAGE = "개";
    private static final String RANK_CORRECT_MESSAGE = "개 일치 (";
    private static final String WIN_PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 손해라는 의미임)";
    public static final String INPUT_MANUAL_COUNT_INSTRUCTION = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    public static void printMoneyInstruction() {
        System.out.println(INPUT_MONEY_INSTRUCTION);
    }

    public static void printManualCountInstruction() {
        System.out.println(INPUT_MANUAL_COUNT_INSTRUCTION);
    }

    public static void printLotto(final List<Lotto> issuedLotto, final int manual) {
        System.out.println(String.format(PURCHASE_MESSAGE, manual, issuedLotto.size() - manual));
        for (Lotto lotto : issuedLotto) {
            System.out.println(lotto.getLotto());
        }
    }

    public static void printWinLottoInstruction() {
        System.out.println();
        System.out.println(INPUT_WIN_LOTTO_INSTRUCTION);
    }

    public static void printBonusInstruction() {
        System.out.println();
        System.out.println(INPUT_BONUS_INSTRUCTION);
    }

    public static void printWinStatistics(final SortedMap<RankPrize, Integer> result) {
        System.out.println();
        System.out.println(WIN_STATISTICS_RESULT_MESSAGE);
        for (Entry<RankPrize, Integer> rankCount : result.entrySet()) {
            printWinStatistics(rankCount);
        }
    }

    private static void printWinStatistics(final Entry<RankPrize, Integer> rankCount) {
        final RankPrize rankPrize = rankCount.getKey();
        if (rankPrize == RankPrize.SECOND) {
            System.out.println(
                    rankPrize.getCount() + SECOND_RANK_CORRECT_MESSAGE + rankPrize.getPrize()
                            + RANK_PRIZE_MESSAGE
                            + rankCount.getValue()
                            + RANK_COUNT_MESSAGE);
            return;
        }
        System.out.println(
                rankPrize.getCount() + RANK_CORRECT_MESSAGE + rankPrize.getPrize() + RANK_PRIZE_MESSAGE
                        + rankCount.getValue() + RANK_COUNT_MESSAGE);
    }

    public static void printWinProfit(final double calculateProfit) {
        System.out.println(String.format(WIN_PROFIT_RESULT_MESSAGE, calculateProfit));
    }

    public static void printManualLottoInstruction() {
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
    }
}
