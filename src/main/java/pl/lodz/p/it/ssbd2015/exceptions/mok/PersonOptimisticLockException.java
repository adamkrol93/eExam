package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa wyjątku związanym z blokadami optymistycznymi
 * Created by adam on 01.05.15.
 */
public class PersonOptimisticLockException extends PersonManagementException{
    public PersonOptimisticLockException(String message) {
        super(message);
    }

    public PersonOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".optimistic_lock";
    }
}
