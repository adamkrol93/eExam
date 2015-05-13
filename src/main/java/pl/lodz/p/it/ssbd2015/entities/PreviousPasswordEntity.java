package pl.lodz.p.it.ssbd2015.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "previous_password")
@TableGenerator(name = "previous_password_id_generator",
        table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "PreviousPasswordEntity",
        allocationSize = 1)
public class PreviousPasswordEntity extends TimeBaseEntity implements Serializable {

    static private Logger logger = LoggerFactory.getLogger(PreviousPasswordEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "previous_password_id_generator")
    @Column(name = "previous_password_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "person_password", nullable = false, length = 32)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "previous_password_date_add", nullable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "previous_password_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "previous_password_version")
    private Long version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", referencedColumnName = "person_id", nullable = false)
    private PersonEntity person;

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreviousPasswordEntity that = (PreviousPasswordEntity) o;

        if (id != that.id) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
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
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "PreviousPasswordEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public void setCreationDate(Calendar date) {
        if (this.getDateAdd() == null) {
            this.setDateAdd(date);
        }
        else {
            logger.warn("{} already has registration date set. Skipping.", this);
        }
    }

    @Override
    public void setModificationDate(Calendar date) {
        setDateModification(date);
    }
}
