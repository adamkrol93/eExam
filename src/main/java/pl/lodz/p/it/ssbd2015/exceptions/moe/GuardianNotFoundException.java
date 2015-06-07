package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący brak szukanego Opiekuna
 * @author Adam Król
 */
public class GuardianNotFoundException extends GuardianManagementException{
    public GuardianNotFoundException(String message) {
        super(message);
    }

    public GuardianNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
