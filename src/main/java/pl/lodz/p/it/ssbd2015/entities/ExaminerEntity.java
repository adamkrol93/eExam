package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@DiscriminatorValue(Groups.EXAMINERGROUP)
@NamedQuery(name = "findExaminerByLogin", query = "SELECT e FROM ExaminerEntity e WHERE e.login = :login")
public class ExaminerEntity extends GroupsEntity {

    @OneToMany(mappedBy = "creator")
    private List<ExamEntity> createdExams = new ArrayList<>();

    @OneToMany(mappedBy = "modifier")
    private List<ExamEntity> modifiedExams = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<QuestionEntity> createdQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "modifier")
    private List<QuestionEntity> modifiedQuestions = new ArrayList<>();

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
}
