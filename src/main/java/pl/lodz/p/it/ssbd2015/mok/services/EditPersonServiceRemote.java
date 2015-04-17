package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.Remote;
import java.security.NoSuchAlgorithmException;

/**
 * Created by marcin on 17.04.15.
 * Interfejs do edycji pól konta (pole Person)
 */
@Remote
public interface EditPersonServiceRemote{
    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna @personEntity
     * @param login login użytkownika po którym będzie wyszukany
     * @return PersonEntity zwrócony zostaje użytkownik wyszukany w bazie danych. Nie może być nullem.
     */
    PersonEntity findPersonForEdit(String login) throws PersonEntityNotFoundException;
    /**
     * Funkcja edycji użytkownika. Edytuje dane uzytkownika
     * @param person dane osobe użytkownika
     */
    void editPerson(PersonEntity person) throws NoSuchAlgorithmException;
}
