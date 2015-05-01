package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
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
        return super.getCode() + ".person_exists";
    }
}
