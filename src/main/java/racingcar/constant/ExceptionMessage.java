package racingcar.constant;

public enum ExceptionMessage {
    NULL_OR_EMPTY_INPUT("값을 입력해주세요."),
    INPUT_TOO_LONG("값이 너무 큽니다."),
    INVALID_NUMBER_FORMAT("숫자만 입력 가능합니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}