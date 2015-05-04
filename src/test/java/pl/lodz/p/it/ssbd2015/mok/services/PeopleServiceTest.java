package pl.lodz.p.it.ssbd2015.mok.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import javax.ejb.EJB;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Andrzej Kurczewski
 */
@UsingDataSet({"ValidUser.yml", "mok/PeopleServiceTest.yml"})
public class PeopleServiceTest extends BaseArquillianTest {

    @EJB
    private PeopleServiceRemote peopleService;

    @Test
    public void shouldReturnTrueForLoginNotInDatabase() throws Exception {
        boolean check = peopleService.checkUniqueness("zajety");
        assertThat(check, is(false));
    }

    @Test
    public void shouldReturnFalseForLoginInDatabase() throws Exception {
        boolean check = peopleService.checkUniqueness("wolny");
        assertThat(check, is(true));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-PeopleServiceTest#shouldInsertPersonWithGroupsToDatabase.yml",
                        excludeColumns = {"groups_id", "person_date_add"})
    public void shouldInsertPersonWithGroupsToDatabase() throws Exception {
        PersonEntity person = new PersonEntity();

        person.setLogin("Pomidor");
        person.setName("Spaghetti");
        person.setLastName("Bolognese");
        person.setPassword("keczup");
        person.setEmail("smaczny@pomidor.pl");

        peopleService.register(person);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mok/expected-PeopleServiceTest#shouldInsertPersonWithGroupsToDatabase.yml",
                        excludeColumns = {"groups_id", "person_date_add"})
    public void shouldInsertPersonWithGroupsToDatabaseIgnoringFieldsNotConnectedWithRegistration() throws Exception {
        PersonEntity person = new PersonEntity();

        person.setLogin("Pomidor");
        person.setName("Spaghetti");
        person.setLastName("Bolognese");
        person.setPassword("keczup");
        person.setEmail("smaczny@pomidor.pl");

        person.setLastTimeLogin(Calendar.getInstance());

        peopleService.register(person);
    }
}