package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący problem z pytaniem
 * @author Andrzej Kurczewski
 */
public class QuestionManagementException extends MzeBaseException {

    public QuestionManagementException(String message) {
        super(message);
    }

    public QuestionManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".question";
    }
}
