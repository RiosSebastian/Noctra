package Rios.tech.Noctra.exception;

public class ProfileLimitExceededException extends RuntimeException {
    public ProfileLimitExceededException(String message) {
        super(message);
    }
}
