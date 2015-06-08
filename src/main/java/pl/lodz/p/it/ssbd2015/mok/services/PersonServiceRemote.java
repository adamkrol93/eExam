package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;

/**
 * Interfejs zdalny służący wyświetlania informacji o użytkowniku i dokonywania na prostych operacji.
 * @author Adam Król
 * @author Michał Sośnicki
 * @author Bartosz Ignaczewski
 */
@Remote
public interface PersonServiceRemote {

    /**
     * Pozwala na wyświetlenie szczegółów własnego konta.
     * Jako ADMINISTRATOR można również wyświetlić szczegóły konta użytkownika wybranego z listy kont.
     * @param login Login użytkownika do odnalezienia.
     * @return Dane odnalezionego użyttkonika.
     * @throws ApplicationBaseException Rzucany, gdy użytkownik o danym loginie nie zostanie odnaleziony.
     */
    PersonEntity getPerson(String login) throws ApplicationBaseException;

    /**
     * Funkcja zwracająca informacje o Użytkowniku aktualnie zalogowanym.
     * Funkcja ustawia również stanową zmienną @personEntity
     * @return Dane odnalezionego użytkownika
     * @throws ApplicationBaseException Rzucany, kiedy nie zwróci informacji
     */
    PersonEntity getLoggedPerson() throws ApplicationBaseException;

    /**
     * Potwierdza użytkownika w systemie, aby ten mógł się zalogować.
     * Aby znaleźć użytkownika do edycji najlepiej wcześniej skorzystać z PersonServiceRemote.getPerson()
     * @throws ApplicationBaseException Rzucany, kiedy nie uda sie potwierdzić użytkownika
     */
    void confirmPerson() throws ApplicationBaseException;

    /**
     * Pozwala na aktywowanie grupy dla uzytkownika.
     * Aby znaleźć użytkownika do edycji najlepiej wcześniej skorzystać z {@link PersonServiceRemote#getPerson(String login)
     * @param id Klucz główny grupy do aktywacji/deaktywacji.
     * @throws ApplicationBaseException Rzucany, kiedy nie uda sie aktywować/dezaktywować grupy
     */
    void toggleGroupActivation(long id) throws ApplicationBaseException;

    /**
     * Pozwala na zablokowanie lub odblokowanie użytkownika.
     * Aby znaleźć użytkownika do edycji należy wcześniej skorzystać z PersonServiceRemote.getPerson()
     * @throws ApplicationBaseException Rzucany, kiedy nie uda sie zablokować lub odblokować użytkownika
     */
    void togglePersonActivation() throws ApplicationBaseException;

}
