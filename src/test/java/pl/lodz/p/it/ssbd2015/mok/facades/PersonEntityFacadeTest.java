package pl.lodz.p.it.ssbd2015.mok.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mre.facades.AnswerEntityFacadeLocal;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.validation.constraints.AssertFalse;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by Marcin on 2015-04-08.
 */
@UsingDataSet({"ValidUser.yml"})
public class PersonEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @Test
    public void testReadLoginPresent() {

        Optional<PersonEntity> foundPerson = personEntityFacade.findByLogin("osoba");

        assertThat("Peron with login = osoba", foundPerson, is(present()));
    }

    @Test
    public void testReadPhrasePresent() {

        List<PersonEntity> foundPerson = personEntityFacade.findByPhrase("oso");

        assertThat("Peron with login like % oso %", foundPerson, hasSize(1));
    }

//    @Test
//    @org.jboss.arquillian.transaction.api.annotation.Transactional(TransactionMode.DISABLED)
//    @ShouldMatchDataSet(value = "mre/expected-AnswerEntityFacadeTest#testMergeAnswer.yml")
//    public void testMergeAnswer() {
//        AnswerEntity answer = answerEntityFacade.findById(9l).get();
//
//        answer.setContent("Dziedziczenie z wykorzystaniem strategii SINGLE nie narusza 3NF.");
//        answer.setDateModification(TestUtils.makeCalendar(2015, 4, 7, 16, 30, 17));
//
//        answerEntityFacade.edit(answer);
//    }

}
