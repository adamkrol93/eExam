package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący brak wyszukiwanego podejścia w bazie danych
 * @author Andrzej Kurczewski
 */
public class ApproachNotFoundException extends ApproachManagementException {
    public ApproachNotFoundException(String message) {
        super(message);
    }

    public ApproachNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
