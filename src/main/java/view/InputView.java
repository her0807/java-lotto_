package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String getInput() {
        return SCANNER.nextLine();
    }

    public static String getMoney() {
        OutputView.printMoneyInstruction();
        return getInput();
    }

    public static String getWinLotto() {
        OutputView.printWinLottoInstruction();
        return getInput();
    }

    public static String getBonusNumber() {
        OutputView.printBonusInstruction();
        return getInput();
    }

    public static String getManualCount() {
        OutputView.printManualCountInstruction();
        return getInput();
    }

    public static String getManualLotto() {
        return getInput();
    }
}
