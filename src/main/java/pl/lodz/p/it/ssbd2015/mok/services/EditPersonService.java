package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
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
public class EditPersonService extends BaseStatefulService implements EditPersonServiceRemote{

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    private PersonEntity personEntity;

    @Override
    public PersonEntity findPersonForEdit(String login) throws PersonEntityNotFoundException {
        if(personEntity == null) {
            this.personEntity = personEntityFacade.findByLogin(login).orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));
        }
        return personEntity;
    }

    @Override
    public void editPerson(PersonEntity person) {
        this.personEntity = personEntityFacade.edit(personEntity);
        personEntity.setName(person.getName());
        personEntity.setLastName(person.getLastName());
        personEntity.setEmail(person.getEmail());
        if(!personEntity.getPassword().equals(person.getPassword())) {
            personEntity.setPassword(PasswordUtils.hashPassword(person.getPassword()));
        }
    }

}
