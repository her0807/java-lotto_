package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.lottonumber.Lotto;
import lotto.domain.matchkind.WinningKind;
import lotto.domain.winningresult.WinningResult;
import lotto.dto.InputLottoDto;
import lotto.dto.LottoNumbersDto;
import lotto.dto.WinningKindDto;
import lotto.util.converter.NumberConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private static final String NUMBER_DELIMITER = ",";

    private LottoMachine lottoMachine;

    public LottoController(final int totalPurchaseAmount, final List<String> manualLottoNumbers) {
        final List<InputLottoDto> manualLottoInfos = splitLottos(manualLottoNumbers);
        this.lottoMachine = new LottoMachine(totalPurchaseAmount, manualLottoInfos);
    }

    private List<InputLottoDto> splitLottos(final List<String> lottoNumbers) {
        return lottoNumbers.stream()
                .map(lottoInfo -> lottoInfo.split(NUMBER_DELIMITER))
                .map(this::convertToInputLottoDtos)
                .map(InputLottoDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> convertToInputLottoDtos(final String[] lottoInfo) {
        return Arrays.stream(lottoInfo)
                .map(String::trim)
                .map(NumberConverter::convertStringToInt)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getAutoPurchaseCount() {
        return lottoMachine.getCountOfAutoLottoNumbers();
    }

    public int getManualPurchaseCount() {
        return lottoMachine.getCountOfManualLottoNumbers();
    }

    public List<LottoNumbersDto> getLottos() {
        final List<Lotto> lottos = lottoMachine.getLottos();
        return lottos.stream()
                .map(Lotto::getValues)
                .map(LottoNumbersDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<WinningKindDto> getWinningResult(final List<Integer> winningLotto, final int bonusNumber) {
        final WinningResult winningResult = lottoMachine.getMatchResult(winningLotto, bonusNumber);
        final Map<WinningKind, Integer> winningNumberByKind = winningResult.getWinningNumberByWinningKind();
        return convertToDto(winningNumberByKind);
    }

    private List<WinningKindDto> convertToDto(final Map<WinningKind, Integer> winningNumberByKind) {
        return winningNumberByKind.keySet().stream()
                .map(winningKind -> new WinningKindDto(winningKind, winningNumberByKind.get(winningKind)))
                .collect(Collectors.toUnmodifiableList());
    }

    public double getProfitRate(final List<Integer> winningLotto, final int bonusNumber) {
        final WinningResult winningResult = lottoMachine.getMatchResult(winningLotto, bonusNumber);
        return winningResult.getProfitRate();
    }
}
