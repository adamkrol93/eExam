package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;

/**
 * Interfejs obsługujący wysyłanie wiadomości E-mail.
 * @author Bartosz Ignaczewski
 */
@Local
public interface EmailManagerLocal {

    /**
     * Metoda do wysyłania wiadomości email poprzez zasób serwera.
     * @param to Adres e-mail na który wiadomość ma dotrzeć
     * @param subject Temat wiadomości (klucz internacjonalizacji)
     * @param body Zawartość wiadomośc (klucz internacjonalizacji)
     * @throws ApplicationBaseException
     */
    void sendEmail(String to, String subject, String body) throws ApplicationBaseException;

}