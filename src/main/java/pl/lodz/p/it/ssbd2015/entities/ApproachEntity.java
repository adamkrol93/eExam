package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "approach")
@TableGenerator(name = "approach_id_generator",
    table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "ApproachEntity",
        allocationSize = 1)
public class ApproachEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "approach_id_generator")
    @Column(name = "approach_id", nullable = false, updatable = false)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approach_date_start", nullable = false)
    private Calendar dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approach_date_end", nullable = false)
    private Calendar dateEnd;

    @Column(name = "approach_disqualification", nullable = false)
    private boolean disqualification;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approach_date_add", nullable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approach_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "approach_version")
    private Long version;

    @OneToMany(mappedBy = "approach", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<AnswerEntity> answers = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "approach_exam_id", referencedColumnName = "exam_id", nullable = false)
    private ExamEntity exam;

    @ManyToOne(optional = false)
    @JoinColumn(name = "approach_entrant_id", referencedColumnName = "groups_id", nullable = false)
    private StudentEntity entrant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "approach_entrant_id", referencedColumnName = "groups_id", nullable = false, insertable = false, updatable = false)
    private StudentStubEntity entrantStub;

    public long getId() {
        return id;
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

    public boolean isDisqualification() {
        return disqualification;
    }

    public void setDisqualification(boolean disqualification) {
        this.disqualification = disqualification;
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

    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }

    public StudentEntity getEntrant() {
        return entrant;
    }

    public void setEntrant(StudentEntity entrant) {
        this.entrant = entrant;
    }

    public StudentStubEntity getEntrantStub() {
        return entrantStub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApproachEntity that = (ApproachEntity) o;

        if (disqualification != that.disqualification) return false;
        if (id != that.id) return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null)
            return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null)
            return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (disqualification ? 1 : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "ApproachEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }
}
