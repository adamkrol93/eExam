package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.EJB;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Andrzej Kurczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/QuestionEntityFacadeTest.yml"})
public class QuestionEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private QuestionEntityFacadeLocal questionEntityFacade;
    @EJB
    private ExamEntityFacadeLocal examEntityFacade;
    @EJB
    private ExaminerEntityFacadeLocal examinerEntityFacade;

    @Test
    public void shouldFindExistingQuestionById() {
        Optional<QuestionEntity> question = questionEntityFacade.findById(1l);

        assertThat("Question with id = 1", question, is(present()));
        assertThat("Exam's title ", question.get(), hasProperty("content", equalTo("'Przykładowe pytanie 1")));
    }

    @Test
    public void shouldNotFindNotExistingQuestionById() {
        Optional<QuestionEntity> question = questionEntityFacade.findById(7l);

        assertThat("Question with id = 7", question, is(not(present())));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-QuestionEntityFacadeTest#shouldSaveQuestionInDatabase.yml")
    public void shouldSaveQuestionInDatabase() {
        ExaminerEntity examiner = examinerEntityFacade.findById(4l).get();

        QuestionEntity question = new QuestionEntity();
        question.setContent("Jak?");
        question.setSampleAnswer("Tak");
        question.setCreator(examiner);
        question.setDateAdd(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 1));

        questionEntityFacade.create(question);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-QuestionEntityFacadeTest#shouldUpdateQuestionInDatabase.yml")
    public void shouldUpdateQuestionInDatabase() {
        QuestionEntity question = questionEntityFacade.findById(1l).get();
        ExaminerEntity examiner = examinerEntityFacade.findById(4l).get();

        question.setSampleAnswer("Nowa przykładowa odpowiedź");
        question.setModifier(examiner);
        question.setDateModification(TestUtils.makeCalendar(2015, 4, 8, 23, 0, 1));

        questionEntityFacade.edit(question);
    }
}