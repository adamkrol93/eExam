package pl.lodz.p.it.ssbd2015.entities.exceptions;

/**
 * Created by adam on 01.05.15.
 */
@javax.ejb.ApplicationException(rollback = true)
public class ApplicationBaseException extends Exception {

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static final String I18N_EXCEPTION_KEY = "application.exception";
}
