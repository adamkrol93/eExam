package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
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
        return super.getCode() + ".person_not_found";
    }
}
