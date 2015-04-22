package pl.lodz.p.it.ssbd2015.mok.services;

import javax.ejb.Remote;
import javax.mail.MessagingException;

/**
 * @author Bartosz Ignaczewski
 */
@Remote
public interface EmailServiceRemote {

	public void sendEmail(String to, String subject, String body) throws MessagingException;

}