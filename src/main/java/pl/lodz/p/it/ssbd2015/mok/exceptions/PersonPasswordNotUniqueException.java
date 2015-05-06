package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
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
