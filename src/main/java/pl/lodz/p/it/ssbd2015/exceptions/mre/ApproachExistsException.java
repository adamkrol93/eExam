package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący próbę zapisania już zapisanego podejścia
 * @author Andrzej Kurczewski
 */
public class ApproachExistsException extends ApproachManagementException {
    public ApproachExistsException(String message) {
        super(message);
    }

    public ApproachExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exists";
    }
}
