package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa stworzona dla wyjątków związanych z przekazananiem nielegalnych lub niestosownych argumentów.
 * @author Adam Król
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
