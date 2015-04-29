package pl.lodz.p.it.ssbd2015.mok.managers;

import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 * @author Bartosz Ignaczewski
 */
@Local
public interface EmailManagerLocal {

	/**
	 * Metoda do wysyłania wiadomości email poprzez zasób serwera.
	 * @param to Adres e-mail na który wiadomość ma dotrzeć
	 * @param subject Temat wiadomości
	 * @param body Zawartość wiadomośc
	 * @throws MessagingException
	 */
	void sendEmail(String to, String subject, String body) throws MessagingException;

}