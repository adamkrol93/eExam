package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
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
