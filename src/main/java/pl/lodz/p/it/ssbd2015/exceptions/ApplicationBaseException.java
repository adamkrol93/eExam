package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasy bazowa dla wyjątków aplikacyjnych.
 * @author Adam Król
 */
@javax.ejb.ApplicationException(rollback = true)
public abstract class ApplicationBaseException extends Exception {

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getCode() {
        return "application.exception";
    }
}
