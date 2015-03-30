package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
        pkColumnValue = "person",
        allocationSize = 1)
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "person_id_generator")
    @Column(name = "person_id", nullable = false, updatable = false)
    private long id;

    @Column(name = "person_login", nullable = false, length = 30, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._-]{4,30}$")
    private String login;

    @Column(name = "person_last_name", nullable = false, length = 30)
    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]{1,30}$")
    private String lastName;

    @Column(name = "person_name", nullable = false, length = 20)
    @Pattern(regexp = "^[a-ząćęłńóśżźA-ZĄĆĘŁŃÓŚŻŹ]{1,20}$")
    private String name;

    @Column(name = "person_password", nullable = false, length = 32)
    private String password;

    @Column(name = "person_email", nullable = false, length = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login", nullable = true)
    private Calendar lastTimeLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login_fail", nullable = true)
    private Calendar lastTimeLoginFail;

    @Column(name = "person_count_login_fail", nullable = true)
    private Integer countLoginFail;

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

    @OneToMany(mappedBy = "person")
    private List<PreviousPasswordEntity> previousPasswords;

    @OneToMany(mappedBy = "person")
    private List<GroupsStubEntity> groupStubs;

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

    public Calendar getLastTimeLoginFail() {
        return lastTimeLoginFail;
    }

    public void setLastTimeLoginFail(Calendar lastTimeLoginFail) {
        this.lastTimeLoginFail = lastTimeLoginFail;
    }

    public Integer getCountLoginFail() {
        return countLoginFail;
    }

    public void setCountLoginFail(Integer countLoginFail) {
        this.countLoginFail = countLoginFail;
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
        if (countLoginFail != null ? !countLoginFail.equals(that.countLoginFail) : that.countLoginFail != null)
            return false;
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
        if (lastTimeLoginFail != null ? !lastTimeLoginFail.equals(that.lastTimeLoginFail) : that.lastTimeLoginFail != null)
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
        result = 31 * result + (lastTimeLoginFail != null ? lastTimeLoginFail.hashCode() : 0);
        result = 31 * result + (countLoginFail != null ? countLoginFail.hashCode() : 0);
        result = 31 * result + (lastIpLogin != null ? lastIpLogin.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (confirm ? 1 : 0);
        result = 31 * result + (dateAdd != null ? dateAdd.hashCode() : 0);
        result = 31 * result + (dateModification != null ? dateModification.hashCode() : 0);
        return result;
    }
}
