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
import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.PreviousPasswordEntity;
import pl.lodz.p.it.ssbd2015.mre.facades.AnswerEntityFacadeLocal;

import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.Calendar;
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
@UsingDataSet({"ValidUser.yml", "mok/PersonEntityFacadeTest.yml"})
public class PersonEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;
    @EJB
    private GroupsStubEntityFacadeLocal grupsEntityFacade;
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

    //MOK.1 Zarejestruj się
    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-ValidUser#testCreatePerson.yml")
    public void testCreatePerson() {
        String login  = "ziomek";
        String lastName = "Kabza";
        String name = "Marcin";
        String password = "9743a66f914cc249efca164485a19c5c";
        String email = "ziomek@test.pl";
        List<PreviousPasswordEntity> previousPasswords = new ArrayList<>();
        List<GroupsStubEntity> groupStubs = new ArrayList<>();

        PersonEntity person = new PersonEntity();
        person.setLogin(login);
        person.setLastName(lastName);
        person.setName(name);
        person.setPassword(password);
        person.setEmail(email);
        person.setLastTimeLogin(null);
        person.setLastTimeLoginFail(null);
        person.setCountLoginFail(null);
        person.setLastIpLogin(null);

        person.setConfirm(true);
        person.setActive(true);
        person.setDateAdd(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 1));
        person.setDateModification(null);
        person.setPreviousPasswords(previousPasswords);
        person.setGroupStubs(groupStubs);

        personEntityFacade.create(person);
    }
    //MOK.2 Potwierdź konto użytkownika
    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-ValidUser#testConfirmPerson.yml")
    public void testConfirmPerson() {

        PersonEntity foundPerson = personEntityFacade.findByLogin("koks").get();
        foundPerson.setConfirm(true);
        personEntityFacade.edit(foundPerson);

    }
    //MOK.3 Edytuj dane personalne i hasło
    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-ValidUser#testEditPerson.yml")
    public void testEditPerson() {

        String email = "test@test.pl";

        PersonEntity foundPerson = personEntityFacade.findByLogin("osoba").get();
        foundPerson.setEmail(email);
        foundPerson.setPassword("9743a66f914cc249efca164485a19c5c");
        //foundPerson.setConfirm(true);
        personEntityFacade.edit(foundPerson);
    }
    //MOK.4 Zablokuj/Odblokuj konto
    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-ValidUser#testBlockPerson.yml")
    public void testBlockPerson() {

        PersonEntity foundPerson = personEntityFacade.findByLogin("osoba").get();
        foundPerson.setActive(false);
        //foundPerson.setConfirm(true);
        personEntityFacade.edit(foundPerson);
    }
    
}