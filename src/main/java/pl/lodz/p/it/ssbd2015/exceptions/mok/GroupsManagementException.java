package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa powiązania z wyjatkami rzucanymi przezz moduł MOK
 * @author Adam Król
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
