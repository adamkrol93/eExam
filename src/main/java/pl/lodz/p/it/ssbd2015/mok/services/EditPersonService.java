package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.managers.PersonManagerLocal;
import pl.lodz.p.it.ssbd2015.mok.utils.PasswordUtils;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

/**
 * Stanowy EJB realizujący interfejs EditPersonServiceRemote.
 * @see EditPersonServiceRemote
 * @author Created by Marcin on 2015-04-17.
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.EditPersonService")
@Interceptors(LoggingInterceptor.class)
public class EditPersonService extends BaseStatefulService implements EditPersonServiceRemote {


    private PersonEntity personEntity;

    @EJB
    private PersonManagerLocal personManager;

    @Override
    public PersonEntity findPersonForEdit(String login) throws PersonEntityNotFoundException {
        if(personEntity == null) {
            this.personEntity = personManager.getPerson(login);
        }
        return personEntity;
    }

    @Override
    public void editPerson(PersonEntity person) {
       personManager.editPerson(this.personEntity,person);
    }

}
