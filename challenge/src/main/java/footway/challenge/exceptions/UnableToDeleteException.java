package footway.challenge.exceptions;

public class UnableToDeleteException extends RuntimeException {
    public UnableToDeleteException(String message) {
        super(message);
    }

    public UnableToDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}