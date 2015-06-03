package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący próbę oceny podejścia, które się jeszcze nie zakończyło
 * @author Piotr Jurewicz
 */
public class ApproachNotEndedException extends ApproachManagementException {
    public ApproachNotEndedException(String message) {
        super(message);
    }

    public ApproachNotEndedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_ended";
    }
}
