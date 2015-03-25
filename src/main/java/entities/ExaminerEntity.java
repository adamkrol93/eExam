package entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.EXAMINERGROUP)
public class ExaminerEntity extends GroupsEntity {

    @OneToMany(mappedBy = "creator")
    private List<ExamEntity> createdExams;

    @OneToMany(mappedBy = "modifier")
    private List<ExamEntity> modifiedExams;

    @OneToMany(mappedBy = "creator")
    private List<QuestionEntity> createdQuestions;

    @OneToMany(mappedBy = "modifier")
    private List<QuestionEntity> modifiedQuestions;

    @ManyToMany(mappedBy = "teacherStubs")
    private List<ExamEntity> exams;

    public List<ExamEntity> getCreatedExams() {
        return createdExams;
    }

    public void setCreatedExams(List<ExamEntity> created) {
        this.createdExams = created;
    }

    public List<ExamEntity> getModifiedExams() {
        return modifiedExams;
    }

    public void setModifiedExams(List<ExamEntity> modified) {
        this.modifiedExams = modified;
    }

    public List<QuestionEntity> getCreatedQuestions() {
        return createdQuestions;
    }

    public void setCreatedQuestions(List<QuestionEntity> createdQuestions) {
        this.createdQuestions = createdQuestions;
    }

    public List<QuestionEntity> getModifiedQuestions() {
        return modifiedQuestions;
    }

    public void setModifiedQuestions(List<QuestionEntity> modifiedQuestions) {
        this.modifiedQuestions = modifiedQuestions;
    }

    public List<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(List<ExamEntity> exams) {
        this.exams = exams;
    }
}
