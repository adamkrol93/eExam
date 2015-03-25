package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "answer")
public class AnswerEntity {
    @Id
//    @TableGenerator(name = "answer_id_counter",
//        table = "id_counter",
//        pkColumnName = "id_counter_id",
//        valueColumnName = "id_counter_value",
//        pkColumnValue = "answer",
//        allocationSize = 10)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "answer_id_counter")
    @Column(name = "answer_id", nullable = false)
    private long id;

    @Column(name = "answer_content", nullable = false, length = 2147483647)
    private String content;

    @Column(name = "answer_grade", nullable = false)
    private int grade;

    @Column(name = "answer_date_add", nullable = false)
    private Timestamp dateAdd;

    @Column(name = "answer_date_modification", nullable = true)
    private Timestamp dateModification;

    @Version
    @Column(name = "answer_version")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "answer_approach_id", referencedColumnName = "approach_id", nullable = false)
    private ApproachEntity approach;

    @ManyToOne
    @JoinColumn(name = "answer_teacher_id", referencedColumnName = "groups_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "answer_teacher_id", referencedColumnName = "groups_id")
    private TeacherStubEntity teacherStub;

    @ManyToOne
    @JoinColumn(name = "answer_question_id", referencedColumnName = "question_id", nullable = false)
    private QuestionEntity question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Timestamp getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Timestamp dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    public ApproachEntity getApproach() {
        return approach;
    }

    public void setApproach(ApproachEntity approach) {
        this.approach = approach;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public TeacherStubEntity getTeacherStub() {
        return teacherStub;
    }

    public void setTeacherStub(TeacherStubEntity teacherStub) {
        this.teacherStub = teacherStub;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (grade != that.grade) return false;
        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null)
            return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null)
            return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + grade;
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }
}
