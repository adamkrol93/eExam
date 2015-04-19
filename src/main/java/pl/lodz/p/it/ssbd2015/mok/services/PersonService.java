package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.GroupsStubEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

/**
 * Stanowy EJB realizujący interfejs PersonServiceRemote.
 * Utrzymuje w sobie pole z użytkownikiem, którego dane są wyświetlane i na którym przeprowadzane są operacje.
 * @author Created by adam on 15.04.15
 * @author Michał Sośnicki
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PersonService")
@Interceptors(LoggingInterceptor.class)
public class PersonService extends BaseStatefulService implements PersonServiceRemote {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;
    @EJB
    private GroupsStubEntityFacadeLocal groupsStubEntityFacade;

    private PersonEntity personEntity;

    @Override
    public PersonEntity getPerson(String login) throws PersonEntityNotFoundException {
        personEntity = personEntityFacade.findByLogin(login)
                .orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));

        personEntity.getGroupStubs().isEmpty();
        return personEntity;
    }

    @Override
    public void confirmPerson() {
        personEntity = personEntityFacade.edit(personEntity);
        personEntity.setConfirm(true);
    }

    @Override
    public void toggleGroupActivation(long id) {
        boolean found = false;

        for (GroupsStubEntity groupsStub : personEntity.getGroupStubs()) {
            if (groupsStub.getId() == id) {
                groupsStub = groupsStubEntityFacade.edit(groupsStub);
                groupsStub.setActive(!groupsStub.isActive());
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("The Person has no Group with id = " + id);
        }

        // TODO: Tutaj użytkownik ma otrzymać maila z powiadomieniem.
    }
}
