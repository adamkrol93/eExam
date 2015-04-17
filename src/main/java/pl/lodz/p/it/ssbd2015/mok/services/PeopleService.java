package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.util.Arrays;

/**
 * @author Andrzej Kurczewski
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
        PersonEntity newPerson = copyRegistrationFields(person);
        newPerson.setActive(true);
        assignAllGroups(newPerson);
        personEntityFacade.create(newPerson);
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

    private PersonEntity copyRegistrationFields(PersonEntity person) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setLogin(person.getLogin());
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setPassword(person.getPassword());
        return newPerson;
    }
}
