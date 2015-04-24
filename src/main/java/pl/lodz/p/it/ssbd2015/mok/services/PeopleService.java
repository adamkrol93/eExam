package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.managers.PersonManagerLocal;
import pl.lodz.p.it.ssbd2015.mok.utils.PasswordUtils;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;
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
 * @author Adam Król
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PeopleService")
@Interceptors(LoggingInterceptor.class)
public class PeopleService extends BaseStatefulService implements PeopleServiceRemote {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @EJB
    private PersonManagerLocal personManager;

    @Override
    public boolean checkUniqueness(String login) {
        return personManager.checkUniqueness(login);
    }

    @Override
    public void register(PersonEntity person) throws MessagingException {
        personManager.register(preparePersonForRegistration(person));
    }

    @Override
    public List<PersonEntity> findAllPeople() {
        return personEntityFacade.findAll();
    }

    private PersonEntity preparePersonForRegistration(PersonEntity person) {
        PersonEntity newPerson = new PersonEntity();
        newPerson.setLogin(person.getLogin());
        newPerson.setEmail(person.getEmail());
        newPerson.setName(person.getName());
        newPerson.setLastName(person.getLastName());
        newPerson.setPassword(person.getPassword());
        return newPerson;
    }

    @Override
    public void correctLogin(String login, String ipAddress, Calendar time) throws PersonEntityNotFoundException {

        PersonEntity personEntity;

        personEntity = personManager.getPerson(login);

        personEntity.setLastTimeLogin(time);
        personEntity.setLastIpLogin(ipAddress);
    }

    @Override
    public List<PersonEntity> findPeopleByPhrase(String phrase){
        return personEntityFacade.findByPhrase(phrase);
    }

}
