package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "groups_name")
@Table(name = "groups")
@SecondaryTable(name = "person", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "person_id")})
public abstract class GroupsEntity {

    @Id
//    @TableGenerator(name = "groups_id_counter",
//        table = "id_counter",
//        pkColumnName = "id_counter_id",
//        valueColumnName = "id_counter_value",
//        pkColumnValue = "groups",
//        allocationSize = 5)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "groups_id_counter")
    @Column(name = "groups_id", nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "groups_name", nullable = false, length = 25)
    private Groups groupName;

    @Column(name = "groups_active", nullable = false)
    private boolean groupActive;

    @Column(name = "groups_date_modification", nullable = true)
    private Timestamp dateModification;

    @Version
    @Column(name = "groups_version")
    private Long version;

    @Column(name = "person_login", table = "person", nullable = false, length = 30)
    private String login;

    @Column(name = "person_last_name", table = "person", nullable = false, length = 35)
    private String lastName;

    @Column(name = "person_name", table = "person", nullable = false, length = 25)
    private String personName;

    @Column(name = "person_password", table = "person", nullable = false, length = 2147483647)
    private String password;

    @Column(name = "person_email", table = "person", nullable = false, length = 2147483647)
    private String email;

    @Column(name = "person_last_time_login", table = "person", nullable = true)
    private Timestamp lastTimeLogin;

    @Column(name = "person_last_time_login_fail", table = "person", nullable = true)
    private Timestamp lastTimeLoginFail;

    @Column(name = "person_count_login_fail", table = "person", nullable = true)
    private Integer countLoginFail;

    @Column(name = "person_last_ip_login", table = "person", nullable = true)
    private String lastIpLogin;

    @Column(name = "person_active", table = "person", nullable = false)
    private boolean personActive;

    @Column(name = "person_confirm", table = "person", nullable = false)
    private boolean confirm;

    @Column(name = "person_date_add", table = "person", nullable = false)
    private Timestamp personDateAdd;

    @Column(name = "person_date_modification", table = "person", nullable = true)
    private Timestamp personDateModification;

    @OneToMany(mappedBy = "person")
    @JoinColumn(name = "person_id", table = "person", referencedColumnName = "person_id")
    private List<PreviousPasswordEntity> previousPasswords;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Timestamp getDateModification() {
        return dateModification;
    }

    public void setDateModification(Timestamp dateModification) {
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

    public Timestamp getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Timestamp lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public Timestamp getLastTimeLoginFail() {
        return lastTimeLoginFail;
    }

    public void setLastTimeLoginFail(Timestamp lastTimeLoginFail) {
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

    public boolean isPersonActive() {
        return personActive;
    }

    public void setPersonActive(boolean personActive) {
        this.personActive = personActive;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public Timestamp getPersonDateAdd() {
        return personDateAdd;
    }

    public void setPersonDateAdd(Timestamp personDateAdd) {
        this.personDateAdd = personDateAdd;
    }

    public Timestamp getPersonDateModification() {
        return personDateModification;
    }

    public void setPersonDateModification(Timestamp personDateModification) {
        this.personDateModification = personDateModification;
    }

    public List<PreviousPasswordEntity> getPreviousPasswords() {
        return previousPasswords;
    }

    public void setPreviousPasswords(List<PreviousPasswordEntity> previousPasswords) {
        this.previousPasswords = previousPasswords;
    }
}
