package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.managers.PersonManagerLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;

/**
 * Stanowy EJB realizujący interfejs EditPersonServiceRemote.
 * @see EditPersonServiceRemote
 * @author Created by Marcin on 2015-04-17.
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.EditPersonService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class EditPersonService extends BaseStatefulService implements EditPersonServiceRemote {

    @EJB
    private PersonManagerLocal personManager;

    @Resource
    private SessionContext sessionContext;

    private PersonEntity personEntity;

    @Override
    @RolesAllowed("EDIT_SOMEBODY_ACCOUNT_MOK")
    public PersonEntity findPersonForEdit(String login) throws ApplicationBaseException {
        personEntity = personManager.getPerson(login);
        return personEntity;
    }

    @Override
    public PersonEntity getLoggedPersonForEdit() throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();
        return personManager.getPerson(login);
    }

    @Override
    @RolesAllowed("EDIT_SOMEBODY_ACCOUNT_MOK")
    public void editPerson(PersonEntity person) throws ApplicationBaseException {
        personManager.editPerson(this.personEntity,person);
    }

    @RolesAllowed("ALL_LOGGED")
    public PersonEntity findLoggedPersonForEdit() throws PersonEntityNotFoundException {
        String login = sessionContext.getCallerPrincipal().getName();
        PersonEntity personEntity = personManager.getPerson(login);
        return personEntity;
    }

}
