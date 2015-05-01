package pl.lodz.p.it.ssbd2015.mok.exceptions;

import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

/**
 * Klasa wyjątku odpowiedzilna za problemy związane z użytkownikami
 * @author Adam Król
 */
public class UserManagementException extends ApplicationBaseException {

    public UserManagementException(String message) {
        super(message);
    }

    public UserManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public static final String LOGIN_IS_NOT_UNIQUE = ApplicationBaseException.I18N_EXCEPTION_KEY + ".login.is.not.unique";
    public static final String PERSON_NOT_FOUND = ApplicationBaseException.I18N_EXCEPTION_KEY +  ".person.not.found";
    public static final String ILLEGAL_ARGUMENT = ApplicationBaseException.I18N_EXCEPTION_KEY +  ".illegal.argument";
    public static final String PERSON_EXISTS = ApplicationBaseException.I18N_EXCEPTION_KEY +  ".person.exists";
    public static final String TRANSACTION_NOT_EXISTS = ApplicationBaseException.I18N_EXCEPTION_KEY + ".transaction.must.exists";
    public static final String UNKNOWN = ApplicationBaseException.I18N_EXCEPTION_KEY + ".unknown";
}
