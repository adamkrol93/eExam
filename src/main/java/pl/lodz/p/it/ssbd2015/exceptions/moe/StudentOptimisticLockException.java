package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek, w założeniu ma opakowywać OptimisticLockException, które może zostać rzucone przez
 * {@link javax.persistence.EntityManager}.merge przy edycji studenta
 * @author Andrzej Kurczewski
 */
public class StudentOptimisticLockException extends StudentManagementException {
    public StudentOptimisticLockException(String message) {
        super(message);
    }

    public StudentOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".optimistic_lock";
    }
}
