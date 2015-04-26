package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

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
     * @return PesonEntity jeżeli została znaleziona
     */
    PersonEntity getPerson(String login) throws PersonEntityNotFoundException;

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
     */
    void register(PersonEntity newPerson) throws MessagingException;

    /**
     * Metoda pomocnicza przyspisąjąca wszystkie grupy do uzytkownika
     * @param person Uzytkownik któremu chcemy przypisać wszystkie grupy
     */
    void assignAllGroups(PersonEntity person);

    /**
     * Metoda sprawdzająca czy dany użytkownik posiada rolę Administratora
     * @param login Login użytkownika do sprawdzenia
     * @param role Obiekt roli któ©y chcemy sprawdzić
     * @return true - jeżeli użytkownik jest administratorem, false - jeżeli użytkownik nie jest administratorem
     * @throws PersonEntityNotFoundException Jeżeli nie znajdziemy użytkownika o podanym loginie
     */
    boolean hasRole(String login, Class<? extends GroupsStubEntity> role) throws PersonEntityNotFoundException;
}
