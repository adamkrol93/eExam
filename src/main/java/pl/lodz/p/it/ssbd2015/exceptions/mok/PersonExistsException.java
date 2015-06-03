package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku sygnalizującego problem z tym iż istnieje w bazie już taki użytkownik
 * @author Michał Sośnicki
 */
public class PersonExistsException extends PersonManagementException {
    public PersonExistsException(String message) {
        super(message);
    }

    public PersonExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exists";
    }
}
