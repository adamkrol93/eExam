package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.ADMIN)
public class AdministratorStubEntity extends GroupsStubEntity {

    @Override
    public String getName() {
        return Groups.ADMIN;
    }

    @Override
    public String toString() {
        return "AdministratorStubEntity{" + super.toString() + "}";
    }
}
