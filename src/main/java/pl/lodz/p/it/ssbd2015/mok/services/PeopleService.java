package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.Groups;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.managers.PersonManagerLocal;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.Calendar;
import java.util.List;

/**
 * Stworzony EJB realizuje interfejs PeopleServiceRemote
 * @see pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote
 * @author Andrzej Kurczewski
 * @author Tobiasz Kowalski
 * @author Adam Król
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PeopleService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class PeopleService extends BaseStatefulService implements PeopleServiceRemote {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @EJB
    private PersonManagerLocal personManager;

    @Override
    @PermitAll
    public boolean checkUniqueness(String login) {
        return personManager.checkUniqueness(login);
    }

    @Override
    @PermitAll
    public void register(PersonEntity person) throws ApplicationBaseException {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setLogin(person.getLogin());
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setPassword(person.getPassword());
        personManager.register(newPerson);
    }

    @Override
    @RolesAllowed("LIST_ACCOUNTS_MOK")
    public List<PersonEntity> findAllPeople() {
        return personEntityFacade.findAll();
    }

    @Override
    public void correctLogin(String login, String ipAddress, Calendar time) throws ApplicationBaseException {
        PersonEntity personEntity;

        personEntity = personManager.getPerson(login);

        personEntity.setLastTimeLogin(time);
        personEntity.setLastIpLogin(ipAddress);
    }

    @Override
    @RolesAllowed("SEARCH_FOR_ACCOUNT_MOK")
    public List<PersonEntity> findPeopleByPhrase(String phrase){
        return personEntityFacade.findByPhrase(phrase);
    }

    @Override
    public boolean isAdministrator() throws ApplicationBaseException {
        return personManager.hasGroup(Groups.ADMIN);
    }

    @Override
    public boolean isStudent() throws ApplicationBaseException {
        return personManager.hasGroup(Groups.STUDENT);
    }

    @Override
    public boolean isTeacher() throws ApplicationBaseException {
        return personManager.hasGroup(Groups.TEACHER);
    }

    @Override
    public boolean isGuardian() throws ApplicationBaseException {
        return personManager.hasGroup(Groups.GUARDIAN);
    }

    @Override
    public boolean isExaminer() throws ApplicationBaseException {
        return personManager.hasGroup(Groups.EXAMINER);
    }
}
