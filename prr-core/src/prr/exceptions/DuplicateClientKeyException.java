package prr.exceptions;

public class DuplicateClientKeyException extends Exception {

    private static final String ERROR_MESSAGE = "Duplicate client key: ";

    public DuplicateClientKeyException(String key) {
        super(ERROR_MESSAGE + key);
    }

    public DuplicateClientKeyException(String key, Exception cause) {
        super(ERROR_MESSAGE + key, cause);
    }

}