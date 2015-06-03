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
@DiscriminatorValue(Groups.TEACHER)
public class TeacherStubEntity extends GroupsStubEntity {

    @OneToMany(mappedBy = "teacherStub")
    private List<AnswerEntity> graded = new ArrayList<>();

    public List<AnswerEntity> getGraded() {
        return graded;
    }

    public void setGraded(List<AnswerEntity> graded) {
        this.graded = graded;
    }

    @Override
    public String getName() {
        return Groups.TEACHER;
    }

    @Override
    public String toString() {
        return "TeacherStubEntity{" + super.toString() + "}";
    }
}
