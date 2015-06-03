package pl.lodz.p.it.ssbd2015.entities.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.TimeBaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

/**
 * Klasa listenera odpowiadającego za ustawienie daty rejestracji oraz modyfikacji encji.
 * @author Tobiasz Kowalski
 */
public class TimeModificationEntityListener {

    private static final Logger logger = LoggerFactory.getLogger(TimeModificationEntityListener.class);

    /**
     * Metoda wywoływana przed wykonaniem operacji persist przez entity managera.
     * Służy do zapisywania czasu tworzenia encji.
     * @param timeModificationEntity Czas zmodyfikowania encji
     */
    @PrePersist
    public void setRegistrationDate(Object timeModificationEntity) {
        ((TimeBaseEntity)timeModificationEntity).setCreationDate(Calendar.getInstance());
    }

    /**
     * Metoda wywoływana przed wykonaniem operacji update na bazie.
     * Służy do zapisywania czasu modyfikacji encji.
     * @param timeModificationEntity Czas zmodyfikowania encji
     */
    @PreUpdate
    public void setModificationDate(Object timeModificationEntity)
    {
        ((TimeBaseEntity)timeModificationEntity).setModificationDate(Calendar.getInstance());
    }
}
