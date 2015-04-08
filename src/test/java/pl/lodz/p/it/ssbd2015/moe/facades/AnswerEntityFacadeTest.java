package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;

import javax.ejb.EJB;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml", "moe/AnswerEntityFacadeTest.yml"})
public class AnswerEntityFacadeTest extends BaseArquillianTest {

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
    public void testChangeGrade()
    {
        Optional<AnswerEntity> foundAnswer = answerEntityFacade.findById(1l);
        AnswerEntity answerEntity = foundAnswer.get();

        answerEntity.setGrade(2);

        answerEntityFacade.edit(answerEntity);
    }

}