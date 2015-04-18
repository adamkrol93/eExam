package pl.lodz.p.it.ssbd2015.entities.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import javax.persistence.PrePersist;
import java.util.Calendar;

/**
 * Klasa listenera odpowiadającego za ustawienie daty rejestracji użytkownika
 * @author Andrzej Kurczewski
 */
public class PersonEntityListener {

    private static Logger logger = LoggerFactory.getLogger(PersonEntityListener.class);

    @PrePersist
    public void setRegistrationDate(PersonEntity person) {
        if (person.getDateAdd() == null) {
            person.setDateAdd(Calendar.getInstance());
        }
        else {
            logger.warn("{} already has registration date set. Ignoring it.", person);
        }
    }
}
