package pl.lodz.p.it.ssbd2015.mok.services;

import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;

import javax.ejb.EJB;

/**
 * @author Bartosz Ignaczewski
 */
public class EmailServiceTest extends BaseArquillianTest {

	@EJB
	private EmailServiceRemote emailService;

	@Test
	public void sendEmail() {
		emailService.sendEmail("foo@foo.bar", "test", "test - body");
	}

}
