package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.Calendar;
import java.util.List;

/**
 * Interfejs EJB wykorzystywany w procesie rejestracji, wyświetlania danych raportowych
 * @author Andrzej Kurczewski
 * @author Adam Król
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
     * Pozwala użytkownikowi na stworzenie konta w systemie.
     * @param person encja reprezentująca dane użytkownika
     * @throws ApplicationBaseException Jeżeli nie zostaną przekazane poprawne dane
     */
    void register(PersonEntity person) throws ApplicationBaseException;

    /**
     * Metoda zwraca listę wszystkich uzytkowników w systemie
     * @return Listę uzytkowników systemu, tzn. listę z obiektami PersonEntity
     */
    List<PersonEntity> findAllPeople();

    /**
     * Użytkownik uwierzytelnia się do systemu. Zapisane do bazy zostaje czas w momencie logowania oraz IP z jakiego nastąpiło logowanie.     * @param login login użytkownika
     * @param ipAddress adres IP
     * @param time czas logowania
     * @throws ApplicationBaseException Rzucany, gdy użytkownik o danym loginie nie zostanie odnaleziony.
     */
    void correctLogin(String login, String ipAddress, Calendar time) throws ApplicationBaseException;

    /**
     * Pozwala na znalezienie kont przypisanych do osób, w których imieniu lub nazwisku występuje dana fraza.
     * @param phrase Szukana fraza
     * @return Listę uzytkowników, tzn. listę z obiektami PersonEntity
     */
    List<PersonEntity> findPeopleByPhrase(String phrase);
}
