package prr.exceptions;

public class DuplicateTerminalKeyException extends Exception {

    private static final String ERROR_MESSAGE = "Duplicate terminal key: ";

    public DuplicateTerminalKeyException(String key) {
        super(ERROR_MESSAGE + key);
    }

    public DuplicateTerminalKeyException(String key, Exception cause) {
        super(ERROR_MESSAGE + key, cause);
    }

}