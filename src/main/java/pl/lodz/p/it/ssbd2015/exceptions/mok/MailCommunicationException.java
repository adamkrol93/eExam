package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku sygnalizującego problem z komunikacją z serwerem mailingowym
 * @author Adam Król
 */
public class MailCommunicationException extends MailException{
    public MailCommunicationException(String message) {
        super(message);
    }

    public MailCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".communication_error";
    }
}
