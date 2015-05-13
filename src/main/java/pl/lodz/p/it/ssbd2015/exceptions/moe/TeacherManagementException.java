package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący problem z nauczycielem
 * @author Andrzej Kurczewski
 */
public class TeacherManagementException extends MoeBaseException {

    public TeacherManagementException(String message) {
        super(message);
    }

    public TeacherManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".teacher";
    }
}
