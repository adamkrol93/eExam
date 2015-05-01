package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.exceptions.UserManagementException;

import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 * Klasa menadżera do zarządzania wszystim co jest powiązane z kontami uzytkownika
 * @author Adam Król
 */
@Local
public interface PersonManagerLocal {

    /**
     * Metoda edyująca dane użytkownika. Zmienia również hasła i sprawdza czy nie jest unikalne.
     * @param oldOne Stara wersj, żeby można było sprawdzić hasło
     * @param newOne Nowa wersja, która przyszła z formularza
     */
    void editPerson(PersonEntity oldOne, PersonEntity newOne);

    /**
     * Metoda pobiera z bazy użytkownika o podanym loginie.
     * @param login Login użytkownika którego szukamy
     * @throws ApplicationBaseException Jeżeli nie znajdzie uzytkownika o podanym loginie
     * @return PesonEntity jeżeli została znaleziona
     */
    PersonEntity getPerson(String login) throws ApplicationBaseException;

    /**
     * Metoda potwierdza przekazanego użytkownika.
     * @param personEntity Uzytkownik, którego chcemy potwierdzić
     */
    void confirmPerson(PersonEntity personEntity);

    /**
     * Metoda zmienia status aktywacji użytkownika
     * @param personEntity Uzytkownik któreo chcemy zmodyfikować
     */
    void togglePersonActivation(PersonEntity personEntity);

    /**
     * Metoda zmienia stan aktywacji grupy.
     * @param personEntity Użytkownik któremu chcemy zmodyfikować aktualne grupy
     * @param id Identyfikator grupy
     * @throws MessagingException
     */
    void toggleGroupActivation(PersonEntity personEntity, long id) throws MessagingException;

    /**
     * Metoda sprawdza czy użytkownik o podanym loginie już istenieje.
     * @param login Login który chcemy sprawdzić, czy jest unikalny
     * @return True - jeżeli nie ma takigo loginu w bazie, False - jeżeli login jest zajęty
     */
    boolean checkUniqueness(String login);

    /**
     * Metoda odpowiada za stworzenie nowego uzytkownika. DO metody przekazujemy obiekt z
     * wypełnionymi danymi podstawowymi. Hasło będzie hashowane później.
     * @param newPerson Dane osoby którą chcemy zarejestrować
     * @throws MessagingException jeżeli nie powiedzie się wysłanie wiadomości
     * @throws ApplicationBaseException Jeżeli dane przekazane do metody są niepoprawne
     */
    void register(PersonEntity newPerson) throws MessagingException, ApplicationBaseException;

    /**
     * Metoda pomocnicza przyspisąjąca wszystkie grupy do uzytkownika
     * @param person Uzytkownik któremu chcemy przypisać wszystkie grupy
     */
    void assignAllGroups(PersonEntity person);

    /**
     * Metoda sprawdzająca czy dany użytkownik posiada daną grupę
     * @param group String grupy który chcemy sprawdzić, musi znajdować się w Groups
     * @return true - jeżeli użytkownik posiada daną rolę, false - jeżeli użytkownik nie posiada danej roli
     * @throws ApplicationBaseException Jeżeli nie znajdziemy użytkownika o podanym loginie
     */
    boolean hasGroup(String group) throws ApplicationBaseException;
}
