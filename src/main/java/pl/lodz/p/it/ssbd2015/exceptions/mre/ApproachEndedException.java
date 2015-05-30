package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący próbę zakończenia już zakończonego podejścia
 * @author Andrzej Kurczewski
 */
public class ApproachEndedException extends ApproachManagementException {
    public ApproachEndedException(String message) {
        super(message);
    }

    public ApproachEndedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".ended";
    }
}
