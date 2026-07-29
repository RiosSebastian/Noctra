package Rios.tech.Noctra.exception;

public class SubscriptionExpiredException extends RuntimeException {
    public SubscriptionExpiredException(String message) {
        super(message);
    }
}