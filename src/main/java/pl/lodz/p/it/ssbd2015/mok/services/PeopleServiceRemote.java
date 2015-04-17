package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import javax.ejb.Remote;

/**
 * Interfejs EJB wykorzystywany w procesie rejestracji, wyświetlania danych raportowych
 * @author Andrzej Kurczewski
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
}
