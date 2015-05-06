package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasa wyjątku sygnalizującego problem ze znalezieniem użytkownika w bazie danych
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class PersonUnknownException extends PersonManagementException {
    public PersonUnknownException(String message) {
        super(message);
    }

    public PersonUnknownException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".unknown";
    }
}
