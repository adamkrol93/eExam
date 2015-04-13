package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@Table(name = "groups")
public abstract class GroupsStubEntity {

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
}
