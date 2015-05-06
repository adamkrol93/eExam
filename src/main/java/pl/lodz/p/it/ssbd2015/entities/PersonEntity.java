package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
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
@Table(name = "person")
@TableGenerator(name = "person_id_generator",
        table = "generator",
        pkColumnName = "class_name",
        valueColumnName = "id_range",
        pkColumnValue = "PersonEntity",
        allocationSize = 1)
@NamedQueries({
        @NamedQuery(
                name = "findPersonByLogin",
                query = "SELECT p FROM PersonEntity p WHERE p.login = :login"
        ),
        @NamedQuery(
                name = "findPersonByPhrase",
                query = "SELECT p FROM PersonEntity p WHERE lower(p.name) like lower(:phrase) or lower(p.lastName) like lower(:phrase)"
        )
})
//@EntityListeners(PersonEntityListener.class)
public class PersonEntity extends TimeModificationBaseClass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "person_id_generator")
    @Column(name = "person_id", nullable = false, updatable = false)
    private long id;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "{person.login.pattern}")
    @Size(min = 4, max = 30, message = "{groups.login.size}")
    @Column(name = "person_login", nullable = false, length = 30, unique = true)
    private String login;

    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]+$", message = "{person.lastname.pattern}")
    @Size(min = 1, max = 30, message = "{groups.lastname.size}")
    @Column(name = "person_last_name", nullable = false, length = 30)
    private String lastName;

    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]+$", message = "{person.name.pattern}")
    @Size(min = 1, max = 20, message = "{groups.name.size}")
    @Column(name = "person_name", nullable = false, length = 20)
    private String name;

    @Column(name = "person_password", nullable = false, length = 32)
    @Size(min = 6, message = "{person.password.size}")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "{person.email.pattern}")
    @Column(name = "person_email", nullable = false, length = 30)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login", nullable = true)
    private Calendar lastTimeLogin;

    @Column(name = "person_last_ip_login", nullable = true)
    private String lastIpLogin;

    @Column(name = "person_active", nullable = false)
    private boolean active;

    @Column(name = "person_confirm", nullable = false)
    private boolean confirm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_date_add", nullable = false)
    private Calendar dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "person_version")
    private Long version;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreviousPasswordEntity> previousPasswords = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<GroupsStubEntity> groupStubs = new ArrayList<>();

    public long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
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

    public List<PreviousPasswordEntity> getPreviousPasswords() {
        return previousPasswords;
    }

    public void setPreviousPasswords(List<PreviousPasswordEntity> previousPasswords) {
        this.previousPasswords = previousPasswords;
    }

    public List<GroupsStubEntity> getGroupStubs() {
        return groupStubs;
    }

    public void setGroupStubs(List<GroupsStubEntity> groupStubs) {
        this.groupStubs = groupStubs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (active != that.active) return false;
        if (confirm != that.confirm) return false;
        if (id != that.id) return false;
        if (dateAdd != null ? !dateAdd.equals(that.dateAdd) : that.dateAdd != null)
            return false;
        if (dateModification != null ? !dateModification.equals(that.dateModification) : that.dateModification != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastIpLogin != null ? !lastIpLogin.equals(that.lastIpLogin) : that.lastIpLogin != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;
        if (lastTimeLogin != null ? !lastTimeLogin.equals(that.lastTimeLogin) : that.lastTimeLogin != null)
            return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastTimeLogin != null ? lastTimeLogin.hashCode() : 0);
        result = 31 * result + (lastIpLogin != null ? lastIpLogin.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (confirm ? 1 : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return "PersonEntity{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    @Override
    public void setCreationDateBase(Calendar date) {
        this.setDateAdd(date);
    }

    @Override
    public void setModificationDateBase(Calendar date) {
        this.setDateModification(date);
    }
}
