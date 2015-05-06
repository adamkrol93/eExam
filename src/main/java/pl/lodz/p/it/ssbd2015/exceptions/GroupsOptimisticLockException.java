package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasa związana z wyjątakami związanymi z blokadami optymistycznymi
 * @author Adam Król
 */
public class GroupsOptimisticLockException extends GroupsManagementException {
    public GroupsOptimisticLockException(String message) {
        super(message);
    }

    public GroupsOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".optimistic_lock";
    }
}
