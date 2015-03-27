package pl.lodz.p.it.ssbd2015.entities;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * Za nazwy odpowiada autor mapowań w glassfish-web.xml
 */
public enum Groups {
    admin("ADMIN", Groups.ADMINGROUP),
    uczeń("STUDENT", Groups.STUDENTGROUP),
    nauczyciel("TEACHER", Groups.TEACHERGROUP),
    opiekun("CURATOR", Groups.CURATORGROUP),
    egzaminator("EXAMINER", Groups.EXAMINERGROUP);

    public static final String ADMINGROUP = "admin";
    public static final String STUDENTGROUP = "uczeń";
    public static final String TEACHERGROUP = "nauczyciel";
    public static final String CURATORGROUP = "opiekun";
    public static final String EXAMINERGROUP = "egzaminator";

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
