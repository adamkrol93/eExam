package pl.lodz.p.it.ssbd2015.moe.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ApproachNotFoundException;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Piotr on 2015-05-23.
 */
@UsingDataSet({"ValidUser.yml", "moe/MarkApproachServiceTest.yml"})
public class MarkApproachServiceTest extends BaseArquillianTest {

    @EJB
    private MarkApproachServiceRemote markApproachService;

    @Test
    public void shouldFindApproach() throws Exception {
        ApproachEntity approachEntity = markApproachService.findById(1L);
        assertNotNull(markApproachService);
    }

    @Test(expected = ApproachNotFoundException.class)
    public void shouldNotFindApproach() throws Exception {
        markApproachService.findById(3L);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-MarkApproachServiceTest#shouldDisqualifyApproach.yml")
    public void shouldDisqualifyApproach() throws Exception {
        ApproachEntity approach = markApproachService.findById(2L);

        markApproachService.disqualify();
    }
}