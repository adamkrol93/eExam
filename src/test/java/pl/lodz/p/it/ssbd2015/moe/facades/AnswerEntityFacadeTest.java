package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Created by adam on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml", "moe/AnswerEntityFacadeTest.yml"})
public class AnswerEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.AnswerEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private AnswerEntityFacadeLocal answerEntityFacade;

        public void getAnswerEntityFacade(Consumer<AnswerEntityFacadeLocal> action) {
            action.accept(answerEntityFacade);
        }

        public <A> A withAnswerEntityFacade(Function<AnswerEntityFacadeLocal, A> action) {
            return action.apply(answerEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private AnswerEntityFacadeLocal answerEntityFacade;

    @Test
    public void testReadPresent() {
        Optional<AnswerEntity> foundAnswer = answerEntityFacade.findById(1l);

        assertThat("answer with id = 1", foundAnswer, is(present()));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-AnswerEntityFacadeTest#testMergeAnswer.yml")
    public void testChangeGrade() {
        Optional<AnswerEntity> foundAnswer = mandatoryWrapper.withAnswerEntityFacade(a -> a.findById(1l));
        AnswerEntity answerEntity = foundAnswer.get();

        answerEntity.setGrade(2);

        mandatoryWrapper.getAnswerEntityFacade(a -> a.edit(answerEntity));
    }

}