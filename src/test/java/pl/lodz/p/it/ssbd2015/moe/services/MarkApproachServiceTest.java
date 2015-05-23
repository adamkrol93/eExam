package pl.lodz.p.it.ssbd2015.moe.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ApproachNotFoundException;

import javax.ejb.EJB;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        markApproachService.findById(2L);
    }
}