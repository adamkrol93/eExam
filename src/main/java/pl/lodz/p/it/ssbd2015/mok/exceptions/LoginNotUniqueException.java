package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
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
