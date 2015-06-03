package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki
 */
@Entity
@DiscriminatorValue(Groups.GUARDIAN)
public class GuardianStubEntity extends GroupsStubEntity {

    @OneToMany(mappedBy = "guardian")
    private List<StudentStubEntity> guarded = new ArrayList<>();

    public List<StudentStubEntity> getGuarded() {
        return guarded;
    }

    public void setGuarded(List<StudentStubEntity> guarded) {
        this.guarded = guarded;
    }

    @Override
    public String getName() {
        return Groups.GUARDIAN;
    }

    @Override
    public String toString() {
        return "GuardianStubEntity{" + super.toString() + "}";
    }
}
