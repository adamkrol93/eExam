package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący niedostępność danego egzaminu dla danego użytkownika w danym momencie.
 * Może być spowodowany wykorzystaniem ilości dostępnych podejść lub niezmieszczeniem się w oknie czasowym egzaminu.
 * @author Piotr Jurewicz
 */
public class UnavailableExamException extends ApproachManagementException {
    public UnavailableExamException(String message) {
        super(message);
    }

    public UnavailableExamException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".unavailable_exam";
    }
}
