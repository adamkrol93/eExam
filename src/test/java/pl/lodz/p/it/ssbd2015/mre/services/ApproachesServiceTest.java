package pl.lodz.p.it.ssbd2015.mre.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Tobiasz Kowalski on 20.05.15.
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
}
