package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "exam")
public class ExamStatsEntity implements Serializable {

    @Id
    @Column(name = "exam_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "exam_title", nullable = false, length = 100, unique = true, updatable = false)
    private String title;

    @Column(name = "exam_count_take_exam", nullable = false, updatable = false)
    private int countTakeExam;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_start", nullable = false, updatable = false)
    private Calendar dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_end", nullable = false, updatable = false)
    private Calendar dateEnd;

    @Column(name = "exam_duration", nullable = false, updatable = false)
    private int duration;

    @Column(name = "exam_count_question", nullable = false, updatable = false)
    private int countQuestion;

    @Column(name = "exam_count_finish_exam", nullable = true)
    private Integer countFinishExam;

    @Column(name = "exam_avg_results", nullable = true, precision = 4)
    private Double avgResults;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_add", nullable = false, updatable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exam_date_modification", nullable = true, updatable = false)
    private Calendar dateModification;

    @OneToMany
    @JoinColumn(name = "approach_exam_id", referencedColumnName = "exam_id", insertable = false, updatable = false)
    private List<ApproachEntity> approaches = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "exam_question",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "question_id", insertable = false, updatable = false)}
    )
    private List<QuestionEntity> questions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "exam_groups",
            joinColumns = {@JoinColumn(name = "exam_id", referencedColumnName = "exam_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "groups_id", referencedColumnName = "groups_id", insertable = false, updatable = false)}
    )
    private List<TeacherEntity> teachers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCountTakeExam() {
        return countTakeExam;
    }

    public Calendar getDateStart() {
        return dateStart;
    }

    public Calendar getDateEnd() {
        return dateEnd;
    }

    public int getDuration() {
        return duration;
    }

    public int getCountQuestion() {
        return countQuestion;
    }

    public Integer getCountFinishExam() {
        return countFinishExam;
    }

    public void setCountFinishExam(Integer countFinishExam) {
        this.countFinishExam = countFinishExam;
    }

    public Double getAvgResults() {
        return avgResults;
    }

    public void setAvgResults(Double avgResults) {
        this.avgResults = avgResults;
    }

    public Calendar getDateAdd() {
        return dateAdd;
    }

    public Calendar getDateModification() {
        return dateModification;
    }

    public List<ApproachEntity> getApproaches() {
        return approaches;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public List<TeacherEntity> getTeachers() {
        return teachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamStatsEntity that = (ExamStatsEntity) o;

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
        result = 31 * result + (avgResults != null ? avgResults.hashCode() : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExamEntity{" +
                "id=" + id +
                '}';
    }
}
