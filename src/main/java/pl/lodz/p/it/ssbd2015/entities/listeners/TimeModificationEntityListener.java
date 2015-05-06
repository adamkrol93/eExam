package pl.lodz.p.it.ssbd2015.entities.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.TimeModificationBaseClass;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Calendar;

/**
 * Klasa listenera odpowiadającego za ustawienie daty rejestracji oraz modyfikacji encji.
 * @author Andrzej Kurczewski
 */
public class TimeModificationEntityListener {

    private static Logger logger = LoggerFactory.getLogger(TimeModificationEntityListener.class);

    @PrePersist
    public void setRegistrationDate(Object timeModificationEntity) {
        ((TimeModificationBaseClass)timeModificationEntity).setCreationDateBase(Calendar.getInstance());
    }

    @PreUpdate
    public void setModificationDate(Object timeModificationEntity)
    {
        ((TimeModificationBaseClass)timeModificationEntity).setModificationDateBase(Calendar.getInstance());
    }
}
