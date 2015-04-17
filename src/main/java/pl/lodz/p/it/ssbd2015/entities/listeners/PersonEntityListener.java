package pl.lodz.p.it.ssbd2015.entities.listeners;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import javax.persistence.PrePersist;
import java.util.Calendar;

/**
 * Klasa listenera odpowiadającego za ustawienie daty rejestracji użytkownika
 * @author Andrzej Kurczewski
 */
public class PersonEntityListener {
    @PrePersist
    public void setRegistrationDate(PersonEntity person) {
        person.setDateAdd(Calendar.getInstance());
    }
}
