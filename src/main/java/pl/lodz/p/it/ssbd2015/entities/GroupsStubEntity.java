package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@Table(name = "groups")
public abstract class GroupsStubEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groups_id_generator")
    @Column(name = "groups_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "groups_active", nullable = false)
    private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "groups_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "groups_version")
    private Long version;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public long getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsStubEntity that = (GroupsStubEntity) o;

        if (id != that.id) return false;
        if (active != that.active) return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        return !(version != null ? !version.equals(that.version) : that.version != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "GroupsStubEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }
}
