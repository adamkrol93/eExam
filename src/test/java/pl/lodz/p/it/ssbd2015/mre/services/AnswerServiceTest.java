package pl.lodz.p.it.ssbd2015.mre.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ApproachEndedException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ApproachNotFoundException;

import javax.ejb.EJB;

import static org.junit.Assert.assertNotNull;


/**
 * @author Marcin Kabza
 * Created by Piotr on 2015-05-25.
 * @author Andrzej Kurczewski
 */
@UsingDataSet({"ValidUser.yml", "mre/AnswerServiceTest.yml"})
public class AnswerServiceTest extends BaseArquillianTest {

    @EJB
    private AnswerServiceRemote answerService;

    @Test
    public void shouldReturnApproach() throws Exception {
        ApproachEntity approachEntity = answerService.findById(1l);
        assertNotNull(approachEntity);
    }

    @Test(expected = ApproachNotFoundException.class)
    public void shouldNotFindApproach() throws Exception {
        answerService.findById(37l);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-AnswerServiceTest#shouldChangeAnswersInFirstApproach.yml")
    public void shouldChangeAnswerInFirstApproach() throws Exception {

        String answerContent = "Przykładowa odpowiedź na pytanie w podejściu 1";

        ApproachEntity approachEntity = answerService.findById(1l);
        for (AnswerEntity answerEntity : approachEntity.getAnswers()) {
            answerEntity.setContent(answerContent);
        }
        answerService.editApproach(approachEntity.getAnswers());
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-AnswerServiceTest#shouldChangeAnswerInFirstApproachOnlyOneAnswer.yml")
    public void shouldChangeAnswerInFirstApproachOnlyOneAnswer() throws Exception {

        String answerContent = "Przykładowa odpowiedź na pytanie w podejściu 1";

        ApproachEntity approachEntity = answerService.findById(1l);
        for (AnswerEntity answerEntity : approachEntity.getAnswers()) {
            if(answerEntity.getId() == 1) {
                answerEntity.setContent(answerContent);
            }
        }
        answerService.editApproach(approachEntity.getAnswers());
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @UsingDataSet({"ValidUser.yml", "mre/AnswerServiceTest#shouldCreateApproach.yml"})
    @ShouldMatchDataSet(value = "mre/expected-AnswerServiceTest#shouldCreateApproach.yml",
            excludeColumns = {"approach.approach_date_start", "approach.approach_date_end", "approach.approach_version"})
    public void shouldCreateApproach() throws Exception {
        answerService.createApproach("Pewien egzamin 1");
    }

    @Test(expected = ApproachEndedException.class)
    @Transactional(TransactionMode.DISABLED)
    public void shouldFailEndingApproachEndedBefore() throws Exception {
        answerService.findById(2);
        answerService.endApproach();
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-AnswerServiceTest#shouldEndApproach.yml", excludeColumns = {"exam.exam_version"})
    public void shouldEndApproach() throws Exception {
        answerService.findById(3);
        answerService.endApproach();
    }
}
