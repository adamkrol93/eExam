package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.CURATORGROUP)
public class CuratorEntity extends GroupsEntity {

    @OneToMany(mappedBy = "guardian")
    private List<StudentEntity> guarded;

    public List<StudentEntity> getGuarded() {
        return guarded;
    }

    public void setGuarded(List<StudentEntity> guarded) {
        this.guarded = guarded;
    }
}
