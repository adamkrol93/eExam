package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

/**
 * Created by adam on 15.04.15.
 * @since 0.0.5
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.PersonService")
@Interceptors(LoggingInterceptor.class)
public class PersonService extends BaseStatefulService implements PersonServiceRemote {

    private PersonEntity personEntity;

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna @personEntity
     * @param login
     * @return
     */
    @Override
    public PersonEntity getPerson(String login) throws PersonEntityNotFoundException {
        if(personEntity == null) {
            this.personEntity = personEntityFacade.findByLogin(login).orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));
        }
        return personEntity;
    }

    /**
     * Funkcja testująca czy można potwierdzać użytkowników
     */
    @Override
    public void confirmPerson() {
        personEntity = personEntityFacade.edit(personEntity);
        personEntity.setConfirm(true);
    }
}
