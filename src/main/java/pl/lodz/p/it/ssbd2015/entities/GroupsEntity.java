package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Table(name = "groups")
@TableGenerator(name = "groups_id_generator",
        table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "GroupsEntity",
        allocationSize = 5)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@SecondaryTable(name = "person", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "person_id")})
public abstract class GroupsEntity extends TimeBaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groups_id_generator")
    @Column(name = "groups_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "groups_active", nullable = false)
    private boolean groupActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "groups_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "groups_version")
    private Long version;

    @Column(name = "person_id", nullable = false, updatable = false)
    private long personId;

    @Column(name = "person_id", table = "person", nullable = false, updatable = false)
    private long personPersonId;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "{groups.login.pattern}")
    @Size(min = 4, max = 30, message = "{groups.login.size}")
    @Column(name = "person_login", table = "person", nullable = false, length = 30, unique = true)
    private String login;

    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]+$", message = "{groups.lastname.pattern}")
    @Size(min = 1, max = 30, message = "{groups.lastname.size}")
    @Column(name = "person_last_name", table = "person", nullable = false, length = 30)
    private String lastName;

    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]+$", message = "{groups.name.pattern}")
    @Size(min = 1, max = 20, message = "{groups.name.size}")
    @Column(name = "person_name", table = "person", nullable = false, length = 20)
    private String personName;

    @Column(name = "person_password", table = "person", nullable = false, length = 32)
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "{groups.email.pattern}")
    @Column(name = "person_email", table = "person", nullable = false, length = 30)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login", table = "person", nullable = true)
    private Calendar lastTimeLogin;

    @Column(name = "person_last_ip_login", table = "person", nullable = true)
    private String lastIpLogin;

    @Column(name = "person_active", table = "person", nullable = false)
    private boolean personActive;

    @Column(name = "person_confirm", table = "person", nullable = false)
    private boolean confirm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_date_add", table = "person", nullable = false)
    private Calendar personDateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_date_modification", table = "person", nullable = true)
    private Calendar personDateModification;

    public long getId() {
        return id;
    }

    public boolean isGroupActive() {
        return groupActive;
    }

    public void setGroupActive(boolean groupActive) {
        this.groupActive = groupActive;
    }

    public Calendar getDateModification() {
        return dateModification;
    }

    public void setDateModification(Calendar dateModification) {
        this.dateModification = dateModification;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Calendar lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public String getLastIpLogin() {
        return lastIpLogin;
    }

    public void setLastIpLogin(String lastIpLogin) {
        this.lastIpLogin = lastIpLogin;
    }

    public boolean isActive() {
        return personActive;
    }

    public void setActive(boolean personActive) {
        this.personActive = personActive;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public Calendar getPersonDateAdd() {
        return personDateAdd;
    }

    public void setPersonDateAdd(Calendar personDateAdd) {
        this.personDateAdd = personDateAdd;
    }

    public Calendar getPersonDateModification() {
        return personDateModification;
    }

    public void setPersonDateModification(Calendar personDateModification) {
        this.personDateModification = personDateModification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (id != that.id) return false;
        if (groupActive != that.groupActive) return false;
        if (personActive != that.personActive) return false;
        if (confirm != that.confirm) return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (personName != null ? !personName.equals(that.personName) : that.personName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastTimeLogin != null ? !lastTimeLogin.equals(that.lastTimeLogin) : that.lastTimeLogin != null)
            return false;
        if (lastIpLogin != null ? !lastIpLogin.equals(that.lastIpLogin) : that.lastIpLogin != null) return false;
        if (personDateAdd != null ? !personDateAdd.equals(that.personDateAdd) : that.personDateAdd != null)
            return false;
        return !(personDateModification != null ? !personDateModification.equals(that.personDateModification) : that.personDateModification != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (groupActive ? 1 : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastTimeLogin != null ? lastTimeLogin.hashCode() : 0);
        result = 31 * result + (lastIpLogin != null ? lastIpLogin.hashCode() : 0);
        result = 31 * result + (personActive ? 1 : 0);
        result = 31 * result + (confirm ? 1 : 0);
        result = 31 * result + (personDateAdd != null ? personDateAdd.hashCode() : 0);
        result = 31 * result + (personDateModification != null ? personDateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "GroupsEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public void setCreationDate(Calendar date) {
    }

    @Override
    public void setModificationDate(Calendar date) {
        this.setDateModification(date);
    }
}
