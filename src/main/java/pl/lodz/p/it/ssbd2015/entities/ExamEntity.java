package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "exam")
@TableGenerator(name = "exam_id_generator",
        table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "exam",
        allocationSize = 1)
public class ExamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "exam_id_generator")
    @Column(name = "exam_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "exam_title", nullable = false, length = 100, unique = true)
    @Pattern("^(\\p{L}|[0-9\\(\\)\\{\\}\\-\\_\\+\\=\\*\\^%$#\\/ ]){1,100}$")
    private String title;

    @Column(name = "exam_count_take_exam", nullable = false)
    private int countTakeExam;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_start", nullable = false)
    private Calendar dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_end", nullable = false)
    private Calendar dateEnd;

    @Temporal(TemporalType.TIME)
    @Column(name = "exam_duration", nullable = false)
    private Calendar duration;

    @Column(name = "exam_count_question", nullable = false)
    private int countQuestion;

    @Column(name = "exam_count_finish_exam", nullable = true)
    private Integer countFinishExam;

    @Column(name = "exam_avg_results", nullable = true, precision = 0)
    private BigInteger avgResults;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_add", nullable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "exam_version")
    private Long version;

    @OneToMany(mappedBy = "exam")
    private List<ApproachEntity> approaches;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exam_creator_id", referencedColumnName = "groups_id", nullable = false)
    private ExaminerEntity creator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exam_creator_id", referencedColumnName = "groups_id", nullable = false, insertable = false, updatable = false)
    private ExaminerStubEntity creatorStub;

    @ManyToOne
    @JoinColumn(name = "exam_modifier_id", referencedColumnName = "groups_id")
    private ExaminerEntity modifier;

    @ManyToOne
    @JoinColumn(name = "exam_modifier_id", referencedColumnName = "groups_id", insertable = false, updatable = false)
    private ExaminerStubEntity modifierStub;

    @ManyToMany
    @JoinTable(name = "exam_question",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "question_id")}
    )
    private List<QuestionEntity> questions;

    @ManyToMany
    @JoinTable(name = "exam_groups",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "groups_id")}
    )
    private List<TeacherEntity> teachers;

    @ManyToMany
    @JoinTable(name = "exam_groups",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "groups_id")}
    )
    private List<TeacherStubEntity> teacherStubs;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountTakeExam() {
        return countTakeExam;
    }

    public void setCountTakeExam(int countTakeExam) {
        this.countTakeExam = countTakeExam;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public void setDateStart(Calendar dateStart) {
        this.dateStart = dateStart;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Calendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Calendar getDuration() {
        return duration;
    }

    public void setDuration(Calendar duration) {
        this.duration = duration;
    }

    public int getCountQuestion() {
        return countQuestion;
    }

    public void setCountQuestion(int countQuestion) {
        this.countQuestion = countQuestion;
    }

    public Integer getCountFinishExam() {
        return countFinishExam;
    }

    public void setCountFinishExam(Integer countFinishExam) {
        this.countFinishExam = countFinishExam;
    }

    public BigInteger getAvgResults() {
        return avgResults;
    }

    public void setAvgResults(BigInteger avgResults) {
        this.avgResults = avgResults;
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

    public List<ApproachEntity> getApproaches() {
        return approaches;
    }

    public void setApproaches(List<ApproachEntity> approaches) {
        this.approaches = approaches;
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

    public void setCreatorStub(ExaminerStubEntity creatorStub) {
        this.creatorStub = creatorStub;
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

    public void setModifierStub(ExaminerStubEntity modifierStub) {
        this.modifierStub = modifierStub;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<TeacherEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherEntity> teachers) {
        this.teachers = teachers;
    }

    public List<TeacherStubEntity> getTeacherStubs() {
        return teacherStubs;
    }

    public void setTeacherStubs(List<TeacherStubEntity> teacherStubs) {
        this.teacherStubs = teacherStubs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamEntity that = (ExamEntity) o;

        if (countQuestion != that.countQuestion) return false;
        if (countTakeExam != that.countTakeExam) return false;
        if (id != that.id) return false;
        if (avgResults != null ? !avgResults.equals(that.avgResults) : that.avgResults != null)
            return false;
        if (countFinishExam != null ? !countFinishExam.equals(that.countFinishExam) : that.countFinishExam != null)
            return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null)
            return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + countTakeExam;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + countQuestion;
        result = 31 * result + (countFinishExam != null ? countFinishExam.hashCode() : 0);
        result = 31 * result + (avgResults != null ? avgResults.hashCode() : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }
}
