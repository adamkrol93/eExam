package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
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
 * @author Adam Król
 * @author Bartosz Ignaczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/ExamListServiceTest.yml"})
public class ExamListServiceTest extends BaseArquillianTest{

    @EJB
    private ExamListServiceRemote examListService;

    @Test
    public void testFindAll() throws Exception {
        List<ExamEntity> exams = examListService.findAll();

        assertThat(exams,hasSize(4));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-ExamListServiceTest#shouldCloneExam.yml",
            excludeColumns = "exam_date_add")
    public void shouldCloneExam() throws Exception {
        examListService.findAll();
        examListService.cloneExam(4l);
    }
}