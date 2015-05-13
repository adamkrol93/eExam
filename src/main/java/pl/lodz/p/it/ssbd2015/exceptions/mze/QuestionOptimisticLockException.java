package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek, w założeniu ma opakowywać OptimisticLockException, które może zostać rzucone przez
 * {@link javax.persistence.EntityManager}.merge przy edycji pytania
 * @author Andrzej Kurczewski
 */
public class QuestionOptimisticLockException extends QuestionManagementException {
    public QuestionOptimisticLockException(String message) {
        super(message);
    }

    public QuestionOptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".optimistic_lock";
    }
}
