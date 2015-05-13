package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku sygnalizującego problem z zakrótnik hasłem podanym przez użytkownika
 * @author Andrzej Kurczewski
 */
public class PasswordTooShortException extends PersonManagementException {
    public PasswordTooShortException(String message) {
        super(message);
    }

    public PasswordTooShortException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".password_too_short";
    }
}
