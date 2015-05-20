package pl.lodz.p.it.ssbd2015.moe.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ApproachNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ExamCreatorForeignKeyException;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
    	ApproachEntity approachEntity = approachesService.findById(1L);
    	assertNotNull(approachEntity);
    }

    @Test(expected = ApproachNotFoundException.class)
    public void shouldNotFindApproach() throws Exception {
    	approachesService.findById(2L);
    }

    @Test
    @UsingDataSet({"ValidUser.yml", "moe/ApproachesServiceTest#shouldGetAllExams.yml"})
    public void shouldGetAllExams() throws Exception {
        List<ExamEntity> exams = approachesService.findAllByLoggedTeacher();
        assertThat("There is a exams list", exams, hasSize(5));
    }
}