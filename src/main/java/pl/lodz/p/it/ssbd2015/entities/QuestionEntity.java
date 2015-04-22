package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "question")
@TableGenerator(name = "question_id_generator",
        table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "QuestionEntity",
        allocationSize = 1)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "question_id_generator")
    @Column(name = "question_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "question_content", nullable = false, length = 255)
    @Size(max = 255, message = "{question.content.size}")
    private String content;

    @Column(name = "question_sample_answer", nullable = false, length = 255)
    @Size(max = 255, message = "{question.sample.size}")
    private String sampleAnswer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "question_date_add", nullable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "question_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "question_version")
    private Long version;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_creator_id", referencedColumnName = "groups_id", nullable = false)
    private ExaminerEntity creator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_creator_id", referencedColumnName = "groups_id", nullable = false, insertable = false, updatable = false)
    private ExaminerStubEntity creatorStub;

    @ManyToOne
    @JoinColumn(name = "question_modifier_id", referencedColumnName = "groups_id")
    private ExaminerEntity modifier;

    @ManyToOne
    @JoinColumn(name = "question_modifier_id", referencedColumnName = "groups_id", insertable = false, updatable = false)
    private ExaminerStubEntity modifierStub;

    @ManyToMany(mappedBy = "questions")
    private List<ExamEntity> exams = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSampleAnswer() {
        return sampleAnswer;
    }

    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }

    public Calendar getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Calendar dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Calendar getDateModification() {
        return dateModification;
    }

    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public ExaminerEntity getCreator() {
        return creator;
    }

    public void setCreator(ExaminerEntity creator) {
        this.creator = creator;
    }

    public ExaminerStubEntity getCreatorStub() {
        return creatorStub;
    }

    public ExaminerEntity getModifier() {
        return modifier;
    }

    public void setModifier(ExaminerEntity modifier) {
        this.modifier = modifier;
    }

    public ExaminerStubEntity getModifierStub() {
        return modifierStub;
    }

    public List<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(List<ExamEntity> exams) {
        this.exams = exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null)
            return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null)
            return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (sampleAnswer != null ? !sampleAnswer.equals(that.sampleAnswer) : that.sampleAnswer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (sampleAnswer != null ? sampleAnswer.hashCode() : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "QuestionEntity{" +
                "id=" + id +
                ", version=" + version +
                ", dateModification=" + (dateModification != null ? timestampFormat.format(dateModification.getTime()) : "null") +
                ", dateAdd=" + (dateAdd != null ? timestampFormat.format(dateAdd.getTime()) : "null") +
                ", sampleAnswer='" + sampleAnswer + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
