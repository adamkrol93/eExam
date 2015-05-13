package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący problem z nauczycielem
 * @author Andrzej Kurczewski
 */
public class AnswerManagementException extends MreBaseException {

    public AnswerManagementException(String message) {
        super(message);
    }

    public AnswerManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".answer";
    }
}
