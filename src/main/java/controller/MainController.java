package controller;

import domain.lotto.LottoFactory;
import domain.lotto.LottoGroup;
import domain.lotto.LottoMoney;
import domain.lotto.WinningLotto;
import domain.result.Result;
import domain.result.TicketCount;
import view.InputView;
import view.ResultView;

public class MainController {
    private MainController() {
    }

    public static MainController create() {
        return new MainController();
    }

    private static float getProfit(final float nowMoney, final float pastMoney) {
        return nowMoney / pastMoney;
    }

    public void run() {
        final LottoMoney lottoMoney = makeLottoMoney();
        final TicketCount count = makeLottoTicketCount(lottoMoney);
        final LottoGroup lottoTickets = makeLottos(count);
        ResultView.printLottoTickets(count, lottoTickets);

        final WinningLotto winningLotto = makeWinNums();

        final Result result = Result.of(lottoTickets, winningLotto);
        end(result, lottoMoney);
    }

    private LottoMoney makeLottoMoney() {
        return LottoMoney.from(
                InputView.inputMoney());
    }

    private TicketCount makeLottoTicketCount(final LottoMoney money) {
        return TicketCount.of(money.toLottoCount(),
                InputView.inputManualTicketCount());
    }

    private LottoGroup makeLottos(final TicketCount count) {
        return LottoFactory.createLottos(count,
                InputView.inputManualNums(count.ofManual()));
    }

    private WinningLotto makeWinNums() {
        return LottoFactory.createWinNums(InputView.inputWinLottoNums(), InputView.inputBonusNumber());
    }

    private void end(final Result result, final LottoMoney lottoMoney) {
        ResultView.printLottosResult(result);
        ResultView.printProfit(getProfit((float) result.getPrize(), (float) lottoMoney.get()));
    }
}