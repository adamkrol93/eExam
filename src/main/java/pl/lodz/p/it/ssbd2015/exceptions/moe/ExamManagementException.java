package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący problem z egzaminem
 * @author Andrzej Kurczewski
 */
public class ExamManagementException extends MoeBaseException {

    public ExamManagementException(String message) {
        super(message);
    }

    public ExamManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exam";
    }
}
