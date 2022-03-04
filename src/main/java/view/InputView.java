package view;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import model.dto.LottoRequestDto;

public class InputView {
    private static final String NOT_INTEGER = "[ERROR] 숫자를 입력해주세요";

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private InputView() {

    }

    public static int inputMoney() throws IOException {
        out.println("구입금액을 입력해 주세요.");
        return inputInteger();
    }

    public static int inputManualLottoCount() throws IOException {
        out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return inputInteger();
    }

    public static List<LottoRequestDto> inputManualLottoNumbers(int manualLottoCount) throws IOException {
        if (manualLottoCount == 0) {
            return Collections.emptyList();
        }
        out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoRequestDto> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoNumbers.add(new LottoRequestDto(inputNumbers()));
        }
        return manualLottoNumbers;
    }

    public static List<Integer> inputWinningNumbers() throws IOException {
        out.println("지난 주 당첨 번호를 입력해 주세요.");
        return inputNumbers();
    }

    public static int inputBonusBall() throws IOException {
        out.println("보너스 볼을 입력해 주세요.");
        return inputInteger();
    }

    private static int inputInteger() throws IOException {
        return convertStringToInt(reader.readLine());
    }

    private static List<Integer> inputNumbers() throws IOException {
        return Arrays.stream(reader.readLine().split(",", -1))
                .map(InputView::convertStringToInt)
                .collect(Collectors.toList());
    }

    private static int convertStringToInt(String intString) {
        try {
            return Integer.parseInt(intString.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER);
        }
    }
}
