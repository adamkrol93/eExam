package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasa wyjątku sygnalizującego problem z hasłem użytkownika
 * @author Andrzej Kurczewski
 */
public class PersonPasswordNotUniqueException extends PersonManagementException {
    public PersonPasswordNotUniqueException(String message) {
        super(message);
    }

    public PersonPasswordNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".password_not_unique";
    }
}
