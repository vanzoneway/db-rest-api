package by.vanzoneway.dbrestapi.core.exception;

public class NotAllowedDatabaseOperationException extends RuntimeException {
    public NotAllowedDatabaseOperationException(String message) {
        super(message);
    }
}
