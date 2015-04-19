package pl.lodz.p.it.ssbd2015.mok.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.Groups;
import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.EJB;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mok/PersonServiceTest.yml"})
public class PersonServiceTest extends BaseArquillianTest {

    @EJB
    private PersonServiceRemote personService;

    @Test
    public void shouldReturnConfirmedPersonWithLogin() throws Exception {
        String login = "osoba";

        PersonEntity found = personService.getPerson(login);

        assertThat("Person with login = " + login + " has been found.", found.getLogin(), is(login));
        assertThat("Person is confirmed.", found.isConfirm());
    }

    @Test
    public void shouldReturnUnconfirmedPersonWithLogin() throws Exception {
        String login = "koks";

        PersonEntity found = personService.getPerson(login);

        assertThat("Person with login = " + login + " has been found.", found.getLogin(), is(login));
        assertThat("Person is unconfirmed.", !found.isConfirm());
    }

    @Test(expected = PersonEntityNotFoundException.class)
    public void shouldThrowExceptionWhenThePersonDoesntExists() throws Exception {
        String login = "niemamnie";

        personService.getPerson(login);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-PersonServiceTest#shouldConfirmUnconfirmedPerson.yml")
    public void shouldConfirmUnconfirmedPerson() throws Exception {
        String login = "koks";

        PersonEntity personBefore = personService.getPerson(login);
        personService.confirmPerson();

        assertThat("The person was unconfirmed before.", !personBefore.isConfirm());
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-PersonServiceTest#shouldActivateGuardianGroup.yml")
    public void shouldActivateGuardianGroup() throws Exception {
        String login = "koks";

        PersonEntity person = personService.getPerson(login);

        for (GroupsStubEntity groupsStub : person.getGroupStubs()) {
            if (groupsStub.getName().equals(Groups.GUARDIAN)) {
                personService.toggleGroupActivation(groupsStub.getId());
            }
        }
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-PersonServiceTest#shouldDeactivatePerson.yml")
    public void shouldDeactivatePerson() throws Exception {
        String login = "koks";

        PersonEntity personBefore = personService.getPerson(login);
        personService.togglePersonActivation();

        assertThat("The person was active before.", personBefore.isActive());
    }

}