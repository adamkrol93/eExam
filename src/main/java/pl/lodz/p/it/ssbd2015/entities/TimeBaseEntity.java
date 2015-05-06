package pl.lodz.p.it.ssbd2015.entities;

import pl.lodz.p.it.ssbd2015.entities.listeners.TimeModificationEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Calendar;

/**
 * Created by Tobiasz Kowalski on 06.05.15.
 * Klasa bazowa dla klas encyjnych.
 * @author tobiasz_kowalski
 */
@MappedSuperclass
@EntityListeners(TimeModificationEntityListener.class)
public abstract class TimeBaseEntity {

    /**
     * Metoda służąca do zapisywania czasu tworzenia encji
     * @param date jako parametr przekazywana jest aktualna data
     */
    public abstract void setCreationDate(Calendar date);

    /**
     * Metoda służąca do zapisywania czasu edycji encji
     * @param date jako parametr przekazywana jest aktualna data
     */
    public abstract void setModificationDate(Calendar date);

}
