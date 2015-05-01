package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
 */
public class GroupsManagementException extends MokBaseException {
    public GroupsManagementException(String message) {
        super(message);
    }

    public GroupsManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".groups";
    }
}
