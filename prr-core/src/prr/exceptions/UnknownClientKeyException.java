package prr.exceptions;

public class UnknownClientKeyException extends Exception {

    private static final String ERROR_MESSAGE = "Unknown client key: ";

    public UnknownClientKeyException(String key) {
        super(ERROR_MESSAGE + key);
    }

    public UnknownClientKeyException(String key, Exception cause) {
        super(ERROR_MESSAGE + key, cause);
    }

}