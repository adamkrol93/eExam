package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.TEACHERGROUP)
public class TeacherEntity extends GroupsEntity {

    @OneToMany(mappedBy = "teacher")
    private List<AnswerEntity> graded;

    @ManyToMany(mappedBy = "teachers")
    private List<ExamEntity> exams;

    public List<AnswerEntity> getGraded() {
        return graded;
    }

    public void setGraded(List<AnswerEntity> graded) {
        this.graded = graded;
    }

    public List<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(List<ExamEntity> exams) {
        this.exams = exams;
    }
}
