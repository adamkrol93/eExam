package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.TEACHERGROUP)
public class TeacherStubEntity extends GroupsStubEntity {

    @OneToMany(mappedBy = "teacherStub")
    private List<AnswerEntity> graded;

    public List<AnswerEntity> getGraded() {
        return graded;
    }

    public void setGraded(List<AnswerEntity> graded) {
        this.graded = graded;
    }
}
