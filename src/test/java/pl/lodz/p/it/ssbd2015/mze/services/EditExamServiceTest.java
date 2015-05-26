package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamOptimisticLockException;

import javax.ejb.EJB;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class EditExamServiceTest extends BaseArquillianTest {

    @EJB
    private EditExamServiceRemote editExamService;

    @EJB
    private EditExamServiceRemote editExamService2;

    @Test
    @Transactional(TransactionMode.DISABLED)
    @UsingDataSet({"ValidUser.yml", "mze/EditExamServiceTest#shouldRemoveAQuestion.yml"})
    @ShouldMatchDataSet(value = "mze/expected-EditExamServiceTest#shouldRemoveAQuestion.yml")
    public void shouldRemoveAQuestion() throws Exception {
        ExamEntity exam = editExamService.findById(3l);  // Przy okazji sprawdzę, czy exam się poprawnie podmieni w środku
        exam = editExamService.findById(1l);

        for (QuestionEntity question : exam.getQuestions()) {
            if (question.getId() == 3) {
                editExamService.removeQuestion(3);
                break;
            }
        }
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @UsingDataSet({"ValidUser.yml", "mze/EditExamServiceTest#shouldRemoveAQuestion.yml"})
    @ShouldMatchDataSet(value = "mze/expected-EditExamServiceTest#shouldRemoveTheOnlyQuestion.yml")
    public void shouldRemoveTheOnly() throws Exception {
        ExamEntity exam = editExamService.findById(2l);
        QuestionEntity theOnlyQuestion = exam.getQuestions().get(0);
        editExamService.removeQuestion(theOnlyQuestion.getId());
    }

    @Test(expected = ExamOptimisticLockException.class)
    @Transactional(TransactionMode.DISABLED)
    @UsingDataSet({"ValidUser.yml", "mze/EditExamServiceTest#shouldRemoveAQuestion.yml"})
    public void shouldRefuseConcurrentRemovalOnTheSameExam() throws Exception {
        ExamEntity exam1 = editExamService.findById(3l);

        ExamEntity exam2 = editExamService.findById(3l);

        editExamService.removeQuestion(2l);

        editExamService.removeQuestion(4l);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @UsingDataSet({"ValidUser.yml", "mze/EditExamServiceTest#shouldRemoveAQuestion.yml"})
    @ShouldMatchDataSet(value = "mze/expected-EditExamServiceTest#shouldRemoveConcurrentlyFromDifferentExams.yml")
    public void shouldRemoveConcurrentlyFromDifferentExams() throws Exception {
        ExamEntity exam1 = editExamService.findById(1l);

        ExamEntity exam2 = editExamService2.findById(3l);

        editExamService.removeQuestion(4l);

        editExamService2.removeQuestion(4l);
    }

}