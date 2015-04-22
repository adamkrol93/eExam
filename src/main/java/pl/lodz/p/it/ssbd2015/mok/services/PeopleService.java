package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.utils.PasswordUtils;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Stworzony EJB realizuje interfejs PeopleServiceRemote
 * @see pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote
 * @author Andrzej Kurczewski
 * @author Tobiasz Kowalski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PeopleService")
@Interceptors(LoggingInterceptor.class)
public class PeopleService extends BaseStatefulService implements PeopleServiceRemote {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @Override
    public boolean checkUniqueness(String login) {
        return !personEntityFacade.findByLogin(login).isPresent();
    }

    @Override
    public void register(PersonEntity person) {
        PersonEntity newPerson = preparePersonForRegistration(person);
        assignAllGroups(newPerson);
        personEntityFacade.create(newPerson);
    }

    @Override
    public List<PersonEntity> findAllPeople() {
        return personEntityFacade.findAll();
    }

    private void assignAllGroups(PersonEntity person) {
        Arrays.asList(new AdministratorStubEntity(), new ExaminerStubEntity(), new GuardianStubEntity(),
                      new StudentStubEntity(), new TeacherStubEntity())
              .forEach(group -> assignGroup(person, group));
    }

    private void assignGroup(PersonEntity person, GroupsStubEntity group) {
        person.getGroupStubs().add(group);
        group.setPerson(person);
    }

    private PersonEntity preparePersonForRegistration(PersonEntity person) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setLogin(person.getLogin());
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setPassword(PasswordUtils.hashPassword(person.getPassword()));
        newPerson.setActive(true);
        return newPerson;
    }

    @Override
    public void correctLogin(String login, String ipAddress, Calendar time) throws PersonEntityNotFoundException {

        PersonEntity personEntity;

        personEntity = personEntityFacade.findByLogin(login).
                orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));

        personEntity.setLastTimeLogin(time);
        personEntity.setLastIpLogin(ipAddress);
    }

    @Override
    public List<PersonEntity> findPeopleByPhrase(String phrase){
        return personEntityFacade.findByPhrase(phrase);
    }

}
