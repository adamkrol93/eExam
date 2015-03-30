package pl.lodz.p.it.ssbd2015.entities;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * Za nazwy odpowiada autor mapowań w glassfish-web.xml
 */
public enum Groups {
    admin("ADMINISTRATOR", Groups.ADMINGROUP),
    uczeń("STUDENT", Groups.STUDENTGROUP),
    nauczyciel("TEACHER", Groups.TEACHERGROUP),
    opiekun("GUARDIAN", Groups.CURATORGROUP),
    egzaminator("EXAMINER", Groups.EXAMINERGROUP);

    public static final String ADMINGROUP = "administrator";
    public static final String STUDENTGROUP = "student";
    public static final String TEACHERGROUP = "teacher";
    public static final String CURATORGROUP = "guardian";
    public static final String EXAMINERGROUP = "examiner";

    private final String roleName;
    private final String groupName;

    Groups(String roleName, String groupName) {
        this.roleName = roleName;
        this.groupName = groupName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getGroupName() {
        return groupName;
    }
}
