package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;

/**
 * Klasa menadżera do zarządzania wszystim co jest powiązane z kontami uzytkownika
 * @author Adam Król
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonManagerLocal {


    /**
     * Metoda edytująca dane użytkownika. Zmienia również hasła i sprawdza czy nie jest unikalne.
     * @param oldOne Stara wersja, żeby można było sprawdzić hasło
     * @param newOne Nowa wersja, która przyszła z formularza
     * @throws ApplicationBaseException Rzucany, kiedy metoda nie zedytuje danych użytkownika
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
     * @throws ApplicationBaseException Rzucany, kiedy nie potwierdzi użytkownika
     */
    void confirmPerson(PersonEntity personEntity) throws ApplicationBaseException;

    /**
     * Metoda zmienia status aktywacji użytkownika
     * @param personEntity Uzytkownik któreo chcemy zmodyfikować
     * @throws ApplicationBaseException Rzucany, kiedy metoda nie zmieni statusu aktywacji użytkownika
     */
    void togglePersonActivation(PersonEntity personEntity) throws ApplicationBaseException;

    /**
     * Metoda zmienia stan aktywacji grupy.
     * @param personEntity Użytkownik któremu chcemy zmodyfikować aktualne grupy
     * @param id Identyfikator grupy
     * @throws ApplicationBaseException Rzucany, kiedy nie zmieni stanu aktywacji grupy
     */
    void toggleGroupActivation(PersonEntity personEntity, long id) throws ApplicationBaseException;

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
     * @throws ApplicationBaseException Jeżeli dane przekazane do metody są niepoprawne
     */
    void register(PersonEntity newPerson) throws  ApplicationBaseException;

    /**
     * Metoda pomocnicza przyspisąjąca wszystkie grupy do uzytkownika
     * @param person Uzytkownik któremu chcemy przypisać wszystkie grupy
     */
    void assignAllGroups(PersonEntity person);
}
