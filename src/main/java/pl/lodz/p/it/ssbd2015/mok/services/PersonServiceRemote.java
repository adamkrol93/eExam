package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;

import javax.ejb.Remote;

/**
 * Interfejs zdalny służący wyświetlania informacji o użytkowniku i dokonywania na prostych operacji.
 * @author Created by adam on 15.04.15
 * @author Michał Sośnicki
 */
@Remote
public interface PersonServiceRemote {

    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna @personEntity
     * @param login Login użytkownika do odnalezienia.
     * @return Dane odnalezionego użyttkonika.
     * @throws PersonEntityNotFoundException Rzucany, gdy użytkownik o danym loginie nie zostanie odnaleziony.
     */
    PersonEntity getPerson(String login) throws PersonEntityNotFoundException;

    /**
     * Funkcja zwracająca informacje o Użytkowniku aktualnie zalogowanym.
     * Funkcja ustawia również stanową zmienną @personEntity
     * @return Dane odnalezionego użytkownika
     * @throws PersonEntityNotFoundException
     */
    PersonEntity getLoggedPerson() throws PersonEntityNotFoundException;

    /**
     * Metoda potwierdzająca odnalezionego wcześniej użytkownika.
     */
    void confirmPerson();

    /**
     * Funkcja aktywuje bądź deaktywuje grupę wczytanego użytkownika o wskazanym kluczu głównym.
     * @param id Klucz główny grupy do aktywacji/deaktywacji.
     */
    void toggleGroupActivation(long id);

    /**
     * Funkcja służy do  blokowania i odblokowywania użytkownika w zależności od bieżącego stanu
     */
    void togglePersonActivation();

}
