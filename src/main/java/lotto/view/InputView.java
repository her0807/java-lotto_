package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> getLottoNumbers(int ticketCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            numbers.add(scanner.nextLine());
        }
        return numbers;
    }
}
