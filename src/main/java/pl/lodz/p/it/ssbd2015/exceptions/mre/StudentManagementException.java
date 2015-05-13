package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący problem ze studentem
 * @author Andrzej Kurczewski
 */
public class StudentManagementException extends MreBaseException {

    public StudentManagementException(String message) {
        super(message);
    }

    public StudentManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".student";
    }
}
