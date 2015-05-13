package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek, w założeniu ma opakowywać OptimisticLockException, które może zostać rzucone przez
 * {@link javax.persistence.EntityManager}.merge przy edycji podejścia
 * @author Andrzej Kurczewski
 */
public class ApproachOptimisticLockException extends ApproachManagementException {
    public ApproachOptimisticLockException(String message) {
        super(message);
    }

    public ApproachOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".optimistic_lock";
    }
}
