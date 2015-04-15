package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.STUDENT)
public class StudentStubEntity extends GroupsStubEntity {

    @ManyToOne
    @JoinColumn(name = "groups_guardian", referencedColumnName = "groups_id")
    private GuardianStubEntity guardian;

    @OneToMany(mappedBy = "entrantStub")
    private List<ApproachEntity> entered = new ArrayList<>();

    public GuardianStubEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianStubEntity guardian) {
        this.guardian = guardian;
    }

    public List<ApproachEntity> getEntered() {
        return entered;
    }

    public void setEntered(List<ApproachEntity> entered) {
        this.entered = entered;
    }

    @Override
    public String toString() {
        return "StudentStubEntity " + super.toString() + "}";
    }
}
