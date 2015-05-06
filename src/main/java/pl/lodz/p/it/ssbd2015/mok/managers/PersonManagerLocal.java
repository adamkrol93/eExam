package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Local;

/**
 * Klasa menadżera do zarządzania wszystim co jest powiązane z kontami uzytkownika
 * @author Adam Król
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonManagerLocal {

    /**
     * Metoda edyująca dane użytkownika. Zmienia również hasła i sprawdza czy nie jest unikalne.
     * @param oldOne Stara wersj, żeby można było sprawdzić hasło
     * @param newOne Nowa wersja, która przyszła z formularza
     */
    void editPerson(PersonEntity oldOne, PersonEntity newOne) throws ApplicationBaseException;

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
    void confirmPerson(PersonEntity personEntity) throws ApplicationBaseException;

    /**
     * Metoda zmienia status aktywacji użytkownika
     * @param personEntity Uzytkownik któreo chcemy zmodyfikować
     */
    void togglePersonActivation(PersonEntity personEntity) throws ApplicationBaseException;

    /**
     * Metoda zmienia stan aktywacji grupy.
     * @param personEntity Użytkownik któremu chcemy zmodyfikować aktualne grupy
     * @param id Identyfikator grupy
     * @throws ApplicationBaseException
     */
    void toggleGroupActivation(PersonEntity personEntity, long id) throws ApplicationBaseException;

    /**
     * Metoda sprawdza czy użytkownik o podanym loginie już istenieje.
     * @param login Login który chcemy sprawdzić, czy jest unikalny
     * @return True - jeżeli nie ma takigo loginu w bazie, False - jeżeli login jest zajęty
     */
    boolean checkUniqueness(String login);

    /**
     * Metoda sprawdza, czy aktualny skrót hasła podanego użytkownika znajduje się też w jego historii haseł
     * @param person Osoba, której hasło jest sprawdzane
     * @return Wartość logiczna true, jeśli skrót występuje w historii, false w przeciwnym przypadku.
     */
    boolean checkIfHashExistsInUserHistory(PersonEntity person);

    /**
     * Metoda odpowiada za stworzenie nowego uzytkownika. DO metody przekazujemy obiekt z
     * wypełnionymi danymi podstawowymi. Hasło będzie hashowane później.
     * @param newPerson Dane osoby którą chcemy zarejestrować
     * @throws ApplicationBaseException Jeżeli dane przekazane do metody są niepoprawne
     */
    void register(PersonEntity newPerson) throws  ApplicationBaseException;

    /**
     * Metoda pomocnicza przyspisąjąca wszystkie grupy do uzytkownika
     * @param person Uzytkownik któremu chcemy przypisać wszystkie grupy
     */
    void assignAllGroups(PersonEntity person);
}
