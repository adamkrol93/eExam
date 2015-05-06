package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasa wyjątku sygnalizującego problem z przekazananiem nielegalnych lub niestosownych argumentów.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class PersonIllegalArgumentException extends PersonManagementException {
    public PersonIllegalArgumentException(String message) {
        super(message);
    }

    public PersonIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
