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
public class CuratorStubEntity extends GroupsStubEntity {

    @OneToMany(mappedBy = "guardian")
    private List<StudentStubEntity> guarded;

    public List<StudentStubEntity> getGuarded() {
        return guarded;
    }

    public void setGuarded(List<StudentStubEntity> guarded) {
        this.guarded = guarded;
    }
}
