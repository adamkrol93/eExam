package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author Bartosz Ignaczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mok.services.EmailManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class EmailManager implements EmailManagerLocal {

	@Resource(lookup = "java:app/email")
	private Session smtpSession;

	@PermitAll
	public void sendEmail(String to, String subject, String body) throws MessagingException {
		MimeMessage message = new MimeMessage(smtpSession);
		message.setFrom(new InternetAddress(smtpSession.getProperty("mail.from")));
		InternetAddress[] address = {new InternetAddress(to)};
		message.setRecipients(Message.RecipientType.TO, address);
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(body, "utf-8", "html");
		message.setSender(new InternetAddress(smtpSession.getProperty("mail.from")));

		Transport.send(message);
	}
}