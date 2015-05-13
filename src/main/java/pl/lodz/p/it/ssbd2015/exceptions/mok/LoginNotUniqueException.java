package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa stworzona dla wyjątków związanych z nie unikalności loginu użytkownika
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class LoginNotUniqueException extends PersonManagementException {
    public LoginNotUniqueException(String message) {
        super(message);
    }

    public LoginNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".login_not_unique";
    }
}
