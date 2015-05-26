package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.QuestionNotFoundException;

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

}