package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
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
