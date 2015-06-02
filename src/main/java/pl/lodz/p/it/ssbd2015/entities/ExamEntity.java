package pl.lodz.p.it.ssbd2015.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        pkColumnValue = "ExamEntity",
        allocationSize = 1)
@NamedQueries(value = {
        @NamedQuery(
                name = "findExamByDate",
                query = "SELECT e FROM ExamEntity e WHERE :date BETWEEN e.dateStart AND e.dateEnd"
        ),
        @NamedQuery(
                name = "findExamByTitle",
                query = "SELECT e FROM ExamEntity e WHERE e.title = :title"
        ),
        @NamedQuery(
                name = "ExamEntity.countFinished",
                query = "SELECT COUNT(a) FROM ApproachEntity a where a.exam = :exam AND :actualdate >= a.dateEnd AND a.disqualification = false"
        ),
        @NamedQuery(
                name = "ExamEntity.examAverage",
                query = "SELECT SUM(an.grade) FROM ApproachEntity a, AnswerEntity an where an.approach = a and a.exam = :exam AND :actualdate >= a.dateEnd AND a.disqualification = false group by a.exam"
        )

})
public class ExamEntity extends TimeBaseEntity implements Serializable {


    static private Logger logger = LoggerFactory.getLogger(PersonEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "exam_id_generator")
    @Column(name = "exam_id", nullable = false, updatable = false)
    private long id;

    @Pattern(regexp = "^(\\p{L}|[0-9\\(\\)\\{\\}\\-_\\+=\\*\\^%$#/\\\\ ])+$", message = "{exam.title.pattern}")
    @Size(min = 1, max = 100, message = "{exam.title.size}")
    @Column(name = "exam_title", nullable = false, length = 100, unique = true)
    private String title;

    @Min(value = 1, message = "{exam.count_take_exam.positive}")
    @Column(name = "exam_count_take_exam", nullable = false)
    private int countTakeExam;

    @NotNull(message = "{exam.date_start.not_null}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_start", nullable = false)
    private Calendar dateStart;

    @NotNull(message = "{exam.date_end.not_null}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_end", nullable = false)
    private Calendar dateEnd;

    @Min(value = 1, message = "{exam.duration.positive}")
    @Column(name = "exam_duration", nullable = false)
    private int duration;

    @Min(value = 1, message = "{exam.count_question.positive}")
    @Column(name = "exam_count_question", nullable = false)
    private int countQuestion;

    @Column(name = "exam_count_finish_exam", nullable = true)
    private Integer countFinishExam;

    @Column(name = "exam_avg_results", nullable = true, precision = 4)
    private double avgResults;

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
    private List<ApproachEntity> approaches = new ArrayList<>();

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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "exam_question",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "question_id")}
    )
    private List<QuestionEntity> questions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "exam_groups",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "groups_id")}
    )
    private List<TeacherEntity> teachers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "exam_groups",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "groups_id")}
    )
    private List<TeacherStubEntity> teacherStubs = new ArrayList<>();

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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

    public double getAvgResults() {
        return avgResults;
    }

    public void setAvgResults(double avgResults) {
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

    public ExaminerEntity getModifier() {
        return modifier;
    }

    public void setModifier(ExaminerEntity modifier) {
        this.modifier = modifier;
    }

    public ExaminerStubEntity getModifierStub() {
        return modifierStub;
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
        if (avgResults == that.avgResults)
            return false;
        if (countFinishExam != null ? !countFinishExam.equals(that.countFinishExam) : that.countFinishExam != null)
            return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null)
            return false;
        if (duration != that.duration) return false;
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
        result = 31 * result + duration;
        result = 31 * result + countQuestion;
        result = 31 * result + (countFinishExam != null ? countFinishExam.hashCode() : 0);
        result = (int) (31 * result + (avgResults));
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "ExamEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public void setCreationDate(Calendar date) {
        if (this.getDateAdd() == null){
            this.setDateAdd(date);
        }
        else {
            logger.warn("{} already has creation date set. Skipping.", this);
        }
    }

    @Override
    public void setModificationDate(Calendar date) {
        this.setDateModification(date);
    }
}
