package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.Remote;
import java.util.Calendar;
import java.util.List;

/**
 * Interfejs EJB wykorzystywany w procesie rejestracji, wyświetlania danych raportowych
 * @author Andrzej Kurczewski
 * @author adam
 */
@Remote
public interface PeopleServiceRemote {
    /**
     * Metoda sprawdzająca w bazie, czy login jest wolny
     * @param login sprawdzany login
     * @return true, jeśli login jest wolny, false w przeciwnym wypadku
     */
    boolean checkUniqueness(String login);
    /**
     * Metoda rejestrująca użytkownika
     * @param person encja reprezentująca dane użytkownika
     */
    void register(PersonEntity person);

    /**
     * Metoda zwraca listę wszystkich uzytkowników w systemie
     * @return Listę uzytkowników systemu, tzn. listę z obiektami PersonEntity
     */
    List<PersonEntity> findAllPeople();

    /**
     * Metoda zapisująca czas w momencie logowania oraz IP z jakiego nastąpiło logowanie w bazie.
     * @param login login użytkownika
     * @param ipAddress adres IP
     * @param time czas logowania
     * @throws PersonEntityNotFoundException Rzucany, gdy użytkownik o danym loginie nie zostanie odnaleziony.
     */
    void correctLogin(String login, String ipAddress, Calendar time) throws PersonEntityNotFoundException;

    /**
     * Metoda zwraca istę użytkowników w którzych imieniu, nazwisku, mailu lub loginie występuje podana fraza.
     * @return Listę uzytkowników, tzn. listę z obiektami PersonEntity
     */
    List<PersonEntity> findPeopleByPhrase(String phrase);
}
