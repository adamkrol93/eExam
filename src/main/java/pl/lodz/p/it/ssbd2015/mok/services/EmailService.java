package pl.lodz.p.it.ssbd2015.mok.services;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author Bartosz Ignaczewski
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmailService implements EmailServiceRemote {

	@Resource(lookup = "email")
	private Session smtpSession;

	private final static Logger logger = Logger.getLogger(EmailService.class.getName());

	public void sendEmail(String to, String subject, String body) {
		MimeMessage message = new MimeMessage(smtpSession);
		try {

			message.setFrom(new InternetAddress(smtpSession.getProperty("mail.from")));
			InternetAddress[] address = {new InternetAddress(to)};
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setText(body, "utf-8", "html");

			Transport.send(message);
		} catch (MessagingException e) {
			logger.severe(() -> "Failed to send email due to MessagingException: " + e.getMessage());
		}
	}
}