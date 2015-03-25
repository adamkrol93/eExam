package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.ADMINGROUP)
public class AdminStubEntity extends GroupsStubEntity {
}
