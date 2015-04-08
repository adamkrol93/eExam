package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.TEACHERGROUP)
@NamedQuery(name = "findByLogin",query = "select t from TeacherEntity t where t.login = :login")
public class TeacherEntity extends GroupsEntity {

    @OneToMany(mappedBy = "teacher")
    private List<AnswerEntity> graded = new ArrayList<>();

    @ManyToMany(mappedBy = "teachers")
    private List<ExamEntity> exams = new ArrayList<>();

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
