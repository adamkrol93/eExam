package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.QuestionNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.QuestionOptimisticLockException;
import pl.lodz.p.it.ssbd2015.mre.services.AnswerServiceRemote;

import javax.ejb.EJB;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Bartosz Ignaczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/EditQuestionServiceTest.yml"})
public class EditQuestionServiceTest extends BaseArquillianTest {

    @EJB
    private EditQuestionServiceRemote questionService;

    @EJB
    private AnswerServiceRemote answerService;

    @Test
    public void shouldFindQuestion() throws Exception {
        QuestionEntity questionEntity = questionService.findById(1l);
        assertThat(questionEntity, notNullValue());
    }

    @Test(expected = QuestionNotFoundException.class)
    public void shouldNotFindQuestion() throws Exception {
        questionService.findById(3l);
    }

    @Test
    public void shouldReturnExistingQuestion() throws Exception {
        long id = 1l;
        QuestionEntity question = questionService.findById(id);
        assertThat(String.format("Question with id = %d can be found.", id), question.getId(), is(id));
    }

    @Test
    @ShouldMatchDataSet(value = "mze/expected-EditQuestionServiceTest#shouldCreateNewQuestionInDatabase.yml",
            excludeColumns = "question_date_add")
    public void shouldCreateNewQuestionInDatabase() throws ApplicationBaseException {
        QuestionEntity question = questionService.findById(2l);
        question.setSampleAnswer("Nowa przykładowa odpowiedź");
        questionService.editQuestion(question);
    }

    @Test
    @ShouldMatchDataSet(value = "mze/expected-EditQuestionServiceTest#shouldUpdateExistingQuestionInDatabase.yml",
            excludeColumns = "question_date_add")
    public void shouldUpdateExistingQuestionInDatabase() throws ApplicationBaseException {
        QuestionEntity question = questionService.findById(1l);
        question.setSampleAnswer("Nowa przykładowa odpowiedź");
        questionService.editQuestion(question);
    }

    @Test(expected = QuestionOptimisticLockException.class)
    @Transactional(TransactionMode.DISABLED)
    public void shouldNotEditQuestionWhenApproachStarts() throws Exception {
        QuestionEntity question = questionService.findById(1l);
        question.setSampleAnswer("Nowa przykładowa odpowiedź");

        answerService.createApproach("Pewien egzamin 1");

        questionService.editQuestion(question);
    }

}