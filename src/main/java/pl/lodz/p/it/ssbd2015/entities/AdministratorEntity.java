package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.ADMIN)
public class AdministratorEntity extends GroupsEntity {
    @Override
    public String toString() {
        return "AdministratorEntity{" + super.toString() + "}";
    }
}
