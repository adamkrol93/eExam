package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacade;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.NoSuchElementException;

/**
 * Created by adam on 15.04.15.
 */
@Stateful
public class PersonService implements PersonServiceRemote {

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
            try {
                this.personEntity = personEntityFacade.findByLogin(login).get();
            }catch(NoSuchElementException e)
            {
                throw new PersonEntityNotFoundException("Nie znaleziono użytkownika o zadanym loginie",e);
            }
        }
        return personEntity;
    }

    /**
     * Funckja testująca czymożna potwierdzać użytkowników
     */
    @Override
    public void confirmPerson() {
        personEntity = personEntityFacade.edit(personEntity);
        personEntity.setConfirm(true);
    }
}
