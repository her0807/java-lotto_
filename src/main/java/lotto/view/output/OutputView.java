package lotto.view.output;

import lotto.dto.WinningKindDto;
import lotto.dto.LottoNumbersDto;

import java.util.List;

public interface OutputView {
    void printPurchaseCount(final int manualPurchaseCount, final int autoPurchaseCount);

    void printLottoNumbersGroup(final List<LottoNumbersDto> lottoNumbersGroup);

    void printCountOfWinningByMatchKind(final List<WinningKindDto> winningResult);

    void printProfitRate(final double profitRate);
}
