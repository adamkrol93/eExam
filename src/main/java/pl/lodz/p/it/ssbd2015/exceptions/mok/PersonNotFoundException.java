package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku sygnalizującego problem z tym iż nie znaleziono danego użytkownika w bazie danych
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class PersonNotFoundException extends PersonManagementException {
    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
