package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasa baza wyjątku sygnalizującego problem z obługą maila
 * @author Adam Król
 */
public abstract class MailException extends MokBaseException {

    public MailException(String message) {
        super(message);
    }

    public MailException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".mail";
    }
}
