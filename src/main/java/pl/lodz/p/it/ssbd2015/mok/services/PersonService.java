package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.GroupsStubEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.managers.PersonManagerLocal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;

/**
 * Stanowy EJB realizujący interfejs PersonServiceRemote.
 * Utrzymuje w sobie pole z użytkownikiem, którego dane są wyświetlane i na którym przeprowadzane są operacje.
 * @author Adam Król
 * @author Michał Sośnicki
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PersonService")
@Interceptors(LoggingInterceptor.class)
public class PersonService extends BaseStatefulService implements PersonServiceRemote {

    @EJB
    private PersonManagerLocal personManager;

    @Resource
    private SessionContext sessionContext;

    private PersonEntity personEntity;



    @Override
    public PersonEntity getPerson(String login) throws PersonEntityNotFoundException {
        personEntity = personManager.getPerson(login);
        return personEntity;
    }

    @Override
    public PersonEntity getLoggedPerson() throws PersonEntityNotFoundException {
        String login =  sessionContext.getCallerPrincipal().getName();
        return personManager.getPerson(login);
    }

    @Override
    public void confirmPerson() {
        personManager.confirmPerson(this.personEntity);
    }

    @Override
    public void toggleGroupActivation(long id) throws MessagingException {
        personManager.toogleGroupActivation(this.personEntity, id);
    }

    @Override
    public void togglePersonActivation() {
        personManager.tooglePersonActivation(this.personEntity);
    }
}
