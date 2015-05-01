package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Klasa wyjątku odpowiedzilna za problemy związane z użytkownikami
 * @author Adam Król
 */
public class PersonManagementException extends MokBaseException {

    public PersonManagementException(String message) {
        super(message);
    }

    public PersonManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".person";
    }
}
