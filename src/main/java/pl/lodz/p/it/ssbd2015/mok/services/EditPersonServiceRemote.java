package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;

/**
 * Interfejs do edycji pól konta (pole Person)
 * @author Created by marcin on 17.04.15.
 */
@Remote
public interface EditPersonServiceRemote {
    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna personEntity
     * @param login login użytkownika po którym będzie wyszukany
     * @return PersonEntity zwrócony zostaje użytkownik wyszukany w bazie danych. Nie może być nullem.
     * @throws ApplicationBaseException Rzucany, kiedy funkcja nie zwróci informacji lub ustawi zmiennej
     */
    PersonEntity findPersonForEdit(String login) throws ApplicationBaseException;

    /**
     * Funkcja zwracająca informacje o Użytkowniku aktualnie zalogowanym.
     * Funkcja ustawia również stanową zmienną @personEntity
     * @return Dane odnalezionego użytkownika
     * @throws ApplicationBaseException Rzucany, kiedy funkcja nie zwróci informacji lub ustawi zmiennej
     */
    PersonEntity findLoggedPersonForEdit() throws ApplicationBaseException;

    /**
     * Pozwala na zmienę danych uzytkownika np. imie, nazwisko czy hasło.
     * ADMINISTRATOR może zmieniać dane każdego użytkownika systemu natomiast pozostali użytkownicy mogą zmieniać tylko
     * swoje dane. Aby znaleźć użytkownika do zmiany najlepiej wcześniej
     * skorzystać z {@link EditPersonServiceRemote#findPersonForEdit(String)}
     * @param person dane osobe użytkownika
     * @throws ApplicationBaseException Rzucany, kiedy nie zedytuje danych użytkownika
     */
    void editPerson(PersonEntity person) throws ApplicationBaseException;
}
