package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa stworzona dla wyjątków związanych z kluczem obcym na tabeli Groups
 * @author Adam Król
 */
public class GroupsGuardianForeignKeyException extends GroupsManagementException {
    public GroupsGuardianForeignKeyException(String message) {
        super(message);
    }

    public GroupsGuardianForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".guardian_foreign_key";
    }
}
