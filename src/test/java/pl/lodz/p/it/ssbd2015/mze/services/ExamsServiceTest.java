package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.EJB;

/**
 * @author Adam Król
 */
@UsingDataSet({"ValidUser.yml","mze/ExamsServiceTest.yml"})
public class ExamsServiceTest extends BaseArquillianTest {

	@EJB
	private ExamsServiceRemote examsService;

	@Test
	@ShouldMatchDataSet(value = "mze/expected-ExamsServiceTest#shouldCreateQuestion.yml",excludeColumns = {"question.question_date_add","question.question_date_modification"})
	public void shouldCreateQuestion() throws Exception
	{

		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setContent("Bla bla bla");
		questionEntity.setSampleAnswer("Pan da 3");
		examsService.create(questionEntity);
	}
}
