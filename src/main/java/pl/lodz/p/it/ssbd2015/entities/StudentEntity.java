package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.STUDENTGROUP)
public class StudentEntity extends GroupsEntity {

    @ManyToOne
    @JoinColumn(name = "groups_guardian", referencedColumnName = "groups_id")
    private GuardianEntity guardian;

    @OneToMany(mappedBy = "entrant")
    private List<ApproachEntity> entered = new ArrayList<>();

    public GuardianEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianEntity guardian) {
        this.guardian = guardian;
    }

    public List<ApproachEntity> getEntered() {
        return entered;
    }

    public void setEntered(List<ApproachEntity> entered) {
        this.entered = entered;
    }
}
