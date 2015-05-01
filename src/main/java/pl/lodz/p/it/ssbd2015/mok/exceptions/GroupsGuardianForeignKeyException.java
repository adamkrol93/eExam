package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
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
