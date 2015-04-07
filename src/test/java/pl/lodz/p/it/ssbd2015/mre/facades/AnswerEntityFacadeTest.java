package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;

import javax.ejb.EJB;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mre/AnswerEntityFacadeTest.yml"})
public class AnswerEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private AnswerEntityFacadeLocal answerEntityFacade;

    @Test
    public void testReadPresent() {
        Optional<AnswerEntity> foundAnswer = answerEntityFacade.findById(1l);

        assertThat("answer with id = 1", foundAnswer, is(present()));
    }

    @Test
    public void testReadNotPresent() {
        Optional<AnswerEntity> foundAnswer = answerEntityFacade.findById(11l);

        assertThat("answer with id = 11", foundAnswer, is(not(present())));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-AnswerEntityFacadeTest#testMergeAnswer.yml")
    public void testMergeAnswer() {
        AnswerEntity answer = answerEntityFacade.findById(9l).get();

        answer.setContent("Dziedziczenie z wykorzystaniem strategii SINGLE nie narusza 3NF.");
        answer.setDateModification(TestUtils.makeCalendar(2015, 4, 7, 16, 30, 17));

        answerEntityFacade.edit(answer);
    }

}