package pl.lodz.p.it.ssbd2015.mre.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ApproachNotFoundException;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

/**
 * @author Created by Tobiasz Kowalski on 20.05.15.
 * @author Andrzej Kurczewski
 * @author Michał Sośnicki
 */
@UsingDataSet({"ValidUser.yml", "mre/ApproachesServiceTest.yml"})
public class ApproachesServiceTest extends BaseArquillianTest {

    @EJB
    ApproachesServiceRemote approachesService;

    @Test
    @Transactional(TransactionMode.DISABLED)
    public void shouldFindAvailableExams() throws Exception {
        List<ExamEntity> exams = approachesService.findAvailableExams();

        assertThat(exams, hasSize(1));
    }

    @Test
    @UsingDataSet({"ValidUser.yml", "mre/ApproachesServiceTest#shouldFindExistingApproach.yml"})
    public void shouldFindExistingApproach() throws Exception {
        ApproachEntity approach = approachesService.findById(1l);
        assertThat("Approach with id = 1 can be found", approach, hasProperty("id", equalTo(1l)));
    }

    @Test(expected = ApproachNotFoundException.class)
    @UsingDataSet({"ValidUser.yml", "mre/ApproachesServiceTest#shouldFindExistingApproach.yml"})
    public void shouldThrowExceptionIfApproachDoesNotExist() throws Exception {
        assertNotNull(approachesService);
        approachesService.findById(74l);
    }

    @Test
    @UsingDataSet({"ValidUser.yml", "mre/ApproachesServiceTest#shouldFindAllStudentApproaches.yml"})
    public void shouldFindAllStudentApproaches() throws Exception {
        List<ApproachEntity> approaches = approachesService.listAllForStudent();
        assertThat("Logged student with id = 3 has 2 approaches", approaches, hasSize(2));
    }

    @Test
    @UsingDataSet({"ValidUser.yml", "mre/ApproachesServiceTest#shouldFindAllStudentApproaches.yml"})
    public void shouldFindAllGuardedStudentApproaches() throws Exception {
        List<ApproachEntity> approaches = approachesService.listAllForGuardian();
        assertThat("Students of the logged guardian have 5 approaches", approaches, hasSize(5));
    }
}

