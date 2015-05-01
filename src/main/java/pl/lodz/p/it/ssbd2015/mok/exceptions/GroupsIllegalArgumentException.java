package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
 */
public class GroupsIllegalArgumentException extends GroupsManagementException {
    public GroupsIllegalArgumentException(String message) {
        super(message);
    }

    public GroupsIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
