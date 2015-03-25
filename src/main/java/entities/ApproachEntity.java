package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "approach")
public class ApproachEntity {

    @Id
//    @TableGenerator(name = "approach_id_counter",
//        table = "id_counter",
//        pkColumnName = "id_counter_id",
//        valueColumnName = "id_counter_value",
//        pkColumnValue = "approach",
//        allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "approach_id_counter")
    @Column(name = "approach_id", nullable = false)
    private long id;

    @Column(name = "approach_date_start", nullable = false)
    private Timestamp dateStart;

    @Column(name = "approach_date_end", nullable = false)
    private Timestamp dateEnd;

    @Column(name = "approach_disqualification", nullable = false)
    private boolean disqualification;

    @Column(name = "approach_date_add", nullable = false)
    private Timestamp dateAdd;

    @Column(name = "approach_date_modification", nullable = true)
    private Timestamp dateModification;

    @Version
    @Column(name = "approach_version")
    private Long version;

    @OneToMany(mappedBy = "approach")
    private List<AnswerEntity> answers;

    @ManyToOne
    @JoinColumn(name = "approach_exam_id", referencedColumnName = "exam_id", nullable = false)
    private ExamEntity exam;

    @ManyToOne
    @JoinColumn(name = "approach_entrant_id", referencedColumnName = "groups_id", nullable = false)
    private StudentEntity entrant;

    @ManyToOne
    @JoinColumn(name = "approach_entrant_id", referencedColumnName = "groups_id", nullable = false)
    private StudentStubEntity entrantStub;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isDisqualification() {
        return disqualification;
    }

    public void setDisqualification(boolean disqualification) {
        this.disqualification = disqualification;
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

    public void setEntrantStub(StudentStubEntity entrantStub) {
        this.entrantStub = entrantStub;
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
}
