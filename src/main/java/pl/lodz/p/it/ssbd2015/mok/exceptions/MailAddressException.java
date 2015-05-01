package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Klasa sygnalizująca błąd z niepoprawnym adresem email lub konfiguracją serwera
 * @author Adam Król
 */
public class MailAddressException extends MailException {
    public MailAddressException(String message) {
        super(message);
    }

    public MailAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".address_error";
    }
}
