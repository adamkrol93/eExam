package pl.lodz.p.it.ssbd2015.entities;

import pl.lodz.p.it.ssbd2015.entities.listeners.TimeModificationEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Calendar;

/**
 * Created by Tobiasz Kowalski on 06.05.15.
 */

@MappedSuperclass
@EntityListeners(TimeModificationEntityListener.class)
public abstract class TimeModificationBaseClass {

    public abstract void setCreationDateBase(Calendar date);
    public abstract void setModificationDateBase(Calendar date);

}
