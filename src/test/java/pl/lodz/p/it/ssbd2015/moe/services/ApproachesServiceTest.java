package pl.lodz.p.it.ssbd2015.moe.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ApproachNotFoundException;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Bartosz Ignaczewski on 12.05.15.
 */
@UsingDataSet({"ValidUser.yml", "moe/ApproachesServiceTest.yml"})
public class ApproachesServiceTest extends BaseArquillianTest {

	@EJB
	private ApproachesServiceRemote approachesService;

	@Test
	public void shouldFindApproach() throws Exception {
		ApproachEntity approachEntity = approachesService.findById(1);
		assertNotNull(approachEntity);
	}

	@Test(expected = ApproachNotFoundException.class)
	public void shouldNotFindApproach() throws Exception {
		approachesService.findById(2);
	}
}