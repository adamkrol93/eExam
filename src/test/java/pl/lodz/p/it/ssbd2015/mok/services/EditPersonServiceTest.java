package pl.lodz.p.it.ssbd2015.mok.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.EJB;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mok/EditPersonServiceTest.yml"})
public class EditPersonServiceTest extends BaseArquillianTest {

    @EJB
    private EditPersonServiceRemote editPersonService;

    @Test
    public void shouldReturnPerson() throws Exception {
        String login = "osoba";

        PersonEntity found = editPersonService.findPersonForEdit(login);

        assertThat("Person with login = " + login + " can be found.", found.getLogin(), is(login));
    }

    @Test(expected = PersonEntityNotFoundException.class)
    public void shouldThrowExceptionWhenThePersonDoesntExists() throws Exception {
        String login = "niemamnie";

        editPersonService.findPersonForEdit(login);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-EditPersonServiceTest#shouldChangePersonNamesInDatabase.yml")
    public void shouldChangeOnlyPersonNameInDatabase() throws Exception {
        String login = "koks";
        PersonEntity person = editPersonService.findPersonForEdit(login);

        person.setName("Jerzy");
        person.setLastName("Jakiśtam");

        editPersonService.editPerson(person);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-EditPersonServiceTest#shouldChangePersonPasswordInDatabase.yml")
    public void shouldChangeOnlyPersonPasswordInDatabase() throws Exception {
        String login = "koks";
        PersonEntity person = editPersonService.findPersonForEdit(login);

        person.setPassword("magicznaKarteczka333");

        editPersonService.editPerson(person);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-EditPersonServiceTest#shouldNotChangeSomeOfThePersonFields.yml")
    public void shouldNotChangeSomeOfThePersonFields() throws Exception {
        String login = "koks";
        PersonEntity person = editPersonService.findPersonForEdit(login);

        person.setActive(true);
        person.setLastIpLogin("dupa");

        editPersonService.editPerson(person);
    }

}