package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek bazowy do sygnalizowania informacji o tym, że jest problem z nauczycielem
 * @author Adam Król
 */
public class TeacherManagementException extends MzeBaseException{
    public TeacherManagementException(String message) {
        super(message);
    }

    public TeacherManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() +".teacher";
    }
}
