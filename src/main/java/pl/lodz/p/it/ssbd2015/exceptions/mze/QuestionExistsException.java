package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący próbę zapisania już zapisanego pytania
 * @author Andrzej Kurczewski
 */
public class QuestionExistsException extends QuestionManagementException {
    public QuestionExistsException(String message) {
        super(message);
    }

    public QuestionExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exists";
    }
}
