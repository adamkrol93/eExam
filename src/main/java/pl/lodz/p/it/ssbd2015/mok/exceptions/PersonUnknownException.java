package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
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
