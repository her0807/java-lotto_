package lotto.controller;

import java.util.ArrayList;
import lotto.domain.*;
import lotto.util.StringConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        final PurchaseAmount purchaseAmount = getPurchaseAmount();
        final LottoMachine lottoMachine = getlottoMachine(purchaseAmount);

        InputView.printGetManualLottosMessage();
        final List<Lotto> lottos = lottoMachine.makeManualAndAutoLottos(getInputManualLottos(lottoMachine.getManualLottoCount()));
        OutputView.printLottos(lottoMachine.getManualLottoCount(), lottoMachine.getAutoLottoCount(), lottos);

        final WinningLotto winningLotto = getWinningLotto();
        final RankBoard rankBoard = new RankBoard(winningLotto, lottos);
        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(StringConverter.toInt(InputView.getAmount()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private LottoMachine getlottoMachine(final PurchaseAmount purchaseAmount) {
        try {
            final int manualLottoCount = StringConverter.toInt(InputView.getManualLottoCount());
            return new LottoMachine(purchaseAmount, manualLottoCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getlottoMachine(purchaseAmount);
        }
    }

    private List<List<Integer>> getInputManualLottos(final int manualLottoCount) {
        try {
            final List<List<Integer>> inputLottos = new ArrayList<>();
            for (int i = 0; i < manualLottoCount; i++) {
                inputLottos.add(StringConverter.toInts(InputView.getManualLotto()));
            }
            return inputLottos;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getInputManualLottos(manualLottoCount);
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            final List<Integer> winningNumbers = StringConverter.toInts(InputView.getWinningNumbers());
            int bonusNumber = StringConverter.toInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
