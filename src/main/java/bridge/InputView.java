package bridge;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    static final String OPEN_STATEMENT = "다리 건너기 게임을 시작합니다.";
    static final String ENTER_BRIDGE_LENGTH = "다리의 길이를 입력해주세요.";
    static final String ERROR_NUMBER = "[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.";
    static final String ERROR_STRING = "[ERROR] 숫자를 입력하여 주세요.";
    static final String ERROR_UP_DOWN = "[ERROR] U 또는 D를 입력하여 주세요.";
    static final String ERROR_RETRY = "[ERROR] R 또는 Q를 입력하여 주세요.";
    static final String MOVE = "이동할 칸을 선택해주세요. (위: U, 아래: D)";
    static final String RETRY = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)";
    static final int UPPER_BOUND = 20;
    static final int LOWER_BOUND = 3;

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(OPEN_STATEMENT);
        System.out.println(ENTER_BRIDGE_LENGTH);
        return catchException();
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(MOVE);
        String input = Console.readLine();
        try {
            upOrDownCheck(input);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            readMoving();
        }
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println(RETRY);
        String redoOrQuit = "";
        try {
            redoOrQuit = Console.readLine();
            redoOrQuitCheck(redoOrQuit);
        } catch (IllegalArgumentException err) {
            System.out.println(err.getMessage());
            return readGameCommand();
        }
        return redoOrQuit;
    }

    public static int catchException() {
        String read = Console.readLine();
        int input;
        try {
            input = isNumeric(read);
            rangeCheck(input);
        } catch (IllegalArgumentException argException) {
            System.out.println(argException.getMessage());
            return catchException();
        }
        return input;
    }

    public static void rangeCheck(int number) {
        if ((number < LOWER_BOUND || number > UPPER_BOUND)) {
            throw new IllegalArgumentException(ERROR_NUMBER);
        }
    }

    public static int isNumeric(String input) {
        int convertInt;
        try {
            convertInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_STRING);
        }
        return convertInt;
    }

    public static void upOrDownCheck(String move) {
        if (!(move.equals(OutputView.UP) || move.equals(OutputView.DOWN)))
            throw new IllegalArgumentException(ERROR_UP_DOWN);
    }

    public static void redoOrQuitCheck(String move) {
        if (!(move.equals(BridgeGame.REDO) || move.equals(BridgeGame.QUIT)))
            throw new IllegalArgumentException(ERROR_RETRY);
    }

}
