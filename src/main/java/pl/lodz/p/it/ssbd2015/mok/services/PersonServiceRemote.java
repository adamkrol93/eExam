package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import javax.mail.MessagingException;

/**
 * Interfejs zdalny służący wyświetlania informacji o użytkowniku i dokonywania na prostych operacji.
 * @author Adam Król
 * @author Michał Sośnicki
 */
@Remote
public interface PersonServiceRemote {

    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna @personEntity
     * @param login Login użytkownika do odnalezienia.
     * @return Dane odnalezionego użyttkonika.
     * @throws ApplicationBaseException Rzucany, gdy użytkownik o danym loginie nie zostanie odnaleziony.
     */
    PersonEntity getPerson(String login) throws ApplicationBaseException;

    /**
     * Funkcja zwracająca informacje o Użytkowniku aktualnie zalogowanym.
     * Funkcja ustawia również stanową zmienną @personEntity
     * @return Dane odnalezionego użytkownika
     * @throws ApplicationBaseException
     */
    PersonEntity getLoggedPerson() throws ApplicationBaseException;

    /**
     * Metoda potwierdzająca odnalezionego wcześniej użytkownika.
     */
    void confirmPerson() throws ApplicationBaseException;

    /**
     * Funkcja aktywuje bądź deaktywuje grupę wczytanego użytkownika o wskazanym kluczu głównym.
     * @param id Klucz główny grupy do aktywacji/deaktywacji.
     * @throws ApplicationBaseException
     */
    void toggleGroupActivation(long id) throws ApplicationBaseException;

    /**
     * Funkcja służy do  blokowania i odblokowywania użytkownika w zależności od bieżącego stanu
     */
    void togglePersonActivation() throws ApplicationBaseException;

}
