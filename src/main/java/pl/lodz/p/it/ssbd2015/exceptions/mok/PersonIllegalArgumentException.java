package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku sygnalizującego problem z przekazananiem nielegalnych lub niestosownych argumentów.
 * @author Michał Sośnicki
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
