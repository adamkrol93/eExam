package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący próbę zapisania już zapisanego egzaminu
 * @author Andrzej Kurczewski
 */
public class ExamExistsException extends ExamManagementException {
    public ExamExistsException(String message) {
        super(message);
    }

    public ExamExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exists";
    }
}
