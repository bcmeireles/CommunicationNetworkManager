package prr.exceptions;

public class InvalidTerminalKeyException extends Exception {

    private static final String ERROR_MESSAGE = "Invalid terminal key: ";

    public InvalidTerminalKeyException(String key) {
        super(ERROR_MESSAGE + key);
    }

    public InvalidTerminalKeyException(String key, Exception cause) {
        super(ERROR_MESSAGE + key, cause);
    }

}