package pl.lodz.p.it.ssbd2015.mok.services;

import javax.ejb.Remote;

/**
 * @author Bartosz Ignaczewski
 */
@Remote
public interface EmailServiceRemote {

	public void sendEmail(String to, String subject, String body);

}
