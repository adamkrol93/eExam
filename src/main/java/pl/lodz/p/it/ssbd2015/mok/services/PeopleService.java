package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        PersonEntity newPerson = preparePersonForRegistration(person);
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

    private PersonEntity preparePersonForRegistration(PersonEntity person) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setLogin(person.getLogin());
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setPassword(hashPassword(person.getPassword()));
        newPerson.setActive(true);
        return newPerson;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Cannot hash password");
            return "";
        }
    }
}
