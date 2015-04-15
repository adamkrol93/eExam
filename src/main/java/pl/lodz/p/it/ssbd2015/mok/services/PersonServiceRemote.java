package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.Remote;

/**
 * Created by adam on 15.04.15.
 * Interfejs do wyświetlania informacji o użytkownikach
 */
@Remote
public interface PersonServiceRemote {

    PersonEntity getPerson(String login) throws PersonEntityNotFoundException;
    void confirmPerson();
}
