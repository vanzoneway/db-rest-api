package by.vanzoneway.dbrestapi.core.exception;

public class PassengerAlreadyHaveCredentials extends RuntimeException {
    public PassengerAlreadyHaveCredentials(String message) {
        super(message);
    }
}
