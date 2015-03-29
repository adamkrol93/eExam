package pl.lodz.p.it.ssbd2015.entities;

import javax.persistence.*;
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
        pkColumnValue = "groups",
        allocationSize = 5)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@SecondaryTable(name = "person", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "person_id")})
public abstract class GroupsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groups_id_generator")
    @Column(name = "groups_id", nullable = false, updatable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "groups_name", nullable = false, length = 25)
    private Groups groupName;

    @Column(name = "groups_active", nullable = false)
    private boolean groupActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "groups_date_modification", nullable = true)
    private Calendar dateModification;

    @Version
    @Column(name = "groups_version")
    private Long version;

    @Column(name = "person_login", table = "person", nullable = false, length = 30, unique = true)
    private String login;

    @Column(name = "person_last_name", table = "person", nullable = false, length = 35)
    private String lastName;

    @Column(name = "person_name", table = "person", nullable = false, length = 25)
    private String personName;

    @Column(name = "person_password", table = "person", nullable = false, length = 2147483647)
    private String password;

    @Column(name = "person_email", table = "person", nullable = false, length = 2147483647)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login", table = "person", nullable = true)
    private Calendar lastTimeLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_last_time_login_fail", table = "person", nullable = true)
    private Calendar lastTimeLoginFail;

    @Column(name = "person_count_login_fail", table = "person", nullable = true)
    private Integer countLoginFail;

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

    public Groups getGroupName() {
        return groupName;
    }

    public void setGroupName(Groups groupName) {
        this.groupName = groupName;
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
}
