package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@Table(name = "groups")
public abstract class GroupsStubEntity {

    @Id
//    @TableGenerator(name = "groups_id_counter",
//        table = "id_counter",
//        pkColumnName = "id_counter_id",
//        valueColumnName = "id_counter_value",
//        pkColumnValue = "groups",
//        allocationSize = 4)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groups_id_counter")
    @Column(name = "groups_id", nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "groups_name", nullable = false, length = 25)
    private Groups name;

    @Column(name = "groups_active", nullable = false)
    private boolean active;

    @Column(name = "groups_date_modification", nullable = true)
    private Timestamp dateModification;

    @Version
    @Column(name = "groups_version")
    private Long version;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Groups getName() {
        return name;
    }

    public void setName(Groups name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
        this.dateModification = dateModification;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
