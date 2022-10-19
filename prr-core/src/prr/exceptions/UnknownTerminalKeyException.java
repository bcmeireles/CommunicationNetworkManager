package prr.exceptions;

public class UnknownTerminalKeyException extends Exception {

    private static final String ERROR_MESSAGE = "Unknown terminal key: ";

    public UnknownTerminalKeyException(String key) {
        super(ERROR_MESSAGE + key);
    }

    public UnknownTerminalKeyException(String key, Exception cause) {
        super(ERROR_MESSAGE + key, cause);
    }

}