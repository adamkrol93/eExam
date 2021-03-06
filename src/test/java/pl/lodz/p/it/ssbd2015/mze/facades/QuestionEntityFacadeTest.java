package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Andrzej Kurczewski
 * @author Bartosz Ignaczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/QuestionEntityFacadeTest.yml"})
public class QuestionEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private QuestionEntityFacadeLocal questionEntityFacade;
        @EJB
        private ExaminerEntityFacadeLocal examinerEntityFacade;
        @EJB
        private ExamEntityFacadeLocal examEntityFacade;

        public void getQuestionEntityFacadeLocal(Consumer<QuestionEntityFacadeLocal> action) {
            action.accept(questionEntityFacade);
        }

        public <A> A withQuestionEntityFacadeLocal(Function<QuestionEntityFacadeLocal, A> action) {
            return action.apply(questionEntityFacade);
        }

        public void getExamEntityFacadeLocal(Consumer<ExamEntityFacadeLocal> action) {
            action.accept(examEntityFacade);
        }

        public <A> A withExamEntityFacadeLocal(Function<ExamEntityFacadeLocal, A> action) {
            return action.apply(examEntityFacade);
        }

        public void getExaminerEntityFacadeLocal(Consumer<ExaminerEntityFacadeLocal> action) {
            action.accept(examinerEntityFacade);
        }

        public <A> A withExaminerEntityFacadeLocal(Function<ExaminerEntityFacadeLocal, A> action) {
            return action.apply(examinerEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
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
    @ShouldMatchDataSet(value = "mze/expected-QuestionEntityFacadeTest#shouldSaveQuestionInDatabase.yml",
            excludeColumns = {"question_date_add"})
    public void shouldSaveQuestionInDatabase() {
        ExaminerEntity examiner = mandatoryWrapper.withExaminerEntityFacadeLocal(e -> e.findById(4l).get());

        QuestionEntity question = new QuestionEntity();
        question.setContent("Jak?");
        question.setSampleAnswer("Tak");
        question.setCreator(examiner);

        mandatoryWrapper.getQuestionEntityFacadeLocal(q -> {
            try {
                q.create(question);
            } catch (ApplicationBaseException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-QuestionEntityFacadeTest#shouldUpdateQuestionInDatabase.yml")
    public void shouldUpdateQuestionInDatabase() {
        QuestionEntity question = mandatoryWrapper.withQuestionEntityFacadeLocal(q -> q.findById(1l).get());
        ExaminerEntity examiner = mandatoryWrapper.withExaminerEntityFacadeLocal(e -> e.findById(4l).get());

        question.setSampleAnswer("Nowa przykładowa odpowiedź");
        question.setModifier(examiner);

        mandatoryWrapper.getQuestionEntityFacadeLocal(q -> {
            try {
                q.edit(question);
            } catch (ApplicationBaseException e) {
                e.printStackTrace();
            }
        });
    }
}
