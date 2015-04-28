package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.Remote;
import javax.mail.MessagingException;
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
     * Metoda rejestrująca użytkownika
     * @param person encja reprezentująca dane użytkownika
     * @throws MessagingException jeżeli nie powiedzie się wysłanie maila
     */
    void register(PersonEntity person) throws MessagingException;

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

    /**
     * Metoda sprawdza czy zalogowana osoba jest Adminstratorem.
     * @return true - jeżeli jest administratorem, false - jeżeli nie jest administratorem
     * @throws PersonEntityNotFoundException jeżeli nie znajdzie zalogowanego użytkownika
     */
    boolean isAdministrator() throws PersonEntityNotFoundException;

    /**
     * Metoda sprawdza czy zalogowana osoba jest Studentem.
     * @return true - jeżeli jest studentem, false - jeżeli nie jest studentem
     * @throws PersonEntityNotFoundException jeżeli nie znajdzie zalogowanego użytkownika
     */
    boolean isStudent() throws PersonEntityNotFoundException;

    /**
     * Metoda sprawdza czy zalogowana osoba jest Nauczycielem (Teacher).
     * @return true - jeżeli jest nauczycielem, false - jeżeli nie jest nauczycielem
     * @throws PersonEntityNotFoundException jeżeli nie znajdzie zalogowanego użytkownika
     */
    boolean isTeacher() throws PersonEntityNotFoundException;

    /**
     * Metoda sprawdza czy zalogowana osoba jest Opiekunem(Guardianem).
     * @return true - jeżeli jest opiekunem, false - jeżeli nie jest opiekunem
     * @throws PersonEntityNotFoundException jeżeli nie znajdzie zalogowanego użytkownika
     */
    boolean isGuardian() throws PersonEntityNotFoundException;

    /**
     * Metoda sprawdza czy zalogowana osoba jest Egzaminatorem(Examiner).
     * @return true - jeżeli jest egzaminatorem, false - jeżeli nie jest egzaminatorem
     * @throws PersonEntityNotFoundException jeżeli nie znajdzie zalogowanego użytkownika
     */
    boolean isExaminer() throws PersonEntityNotFoundException;
}
