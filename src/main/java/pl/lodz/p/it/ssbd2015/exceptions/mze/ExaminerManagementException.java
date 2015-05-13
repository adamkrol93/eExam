package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący problem z egzaminatorem
 * @author Andrzej Kurczewski
 */
public class ExaminerManagementException extends MzeBaseException {

    public ExaminerManagementException(String message) {
        super(message);
    }

    public ExaminerManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".examiner";
    }
}
