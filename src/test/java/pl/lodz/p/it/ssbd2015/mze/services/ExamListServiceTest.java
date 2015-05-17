package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Adam Król
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
}