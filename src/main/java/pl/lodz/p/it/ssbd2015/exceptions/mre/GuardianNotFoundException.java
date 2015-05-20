package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek rzucany, gdy nie uda się znaleźć żądanego opiekuna.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class GuardianNotFoundException extends GuardianManagementException {

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
