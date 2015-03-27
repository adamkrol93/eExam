package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.STUDENTGROUP)
public class StudentEntity extends GroupsEntity {

    @ManyToOne
    @JoinColumn(name = "groups_guardian", referencedColumnName = "groups_id")
    private CuratorEntity guardian;

    @OneToMany(mappedBy = "entrant")
    private List<ApproachEntity> entered;

    public CuratorEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(CuratorEntity guardian) {
        this.guardian = guardian;
    }

    public List<ApproachEntity> getEntered() {
        return entered;
    }

    public void setEntered(List<ApproachEntity> entered) {
        this.entered = entered;
    }
}
