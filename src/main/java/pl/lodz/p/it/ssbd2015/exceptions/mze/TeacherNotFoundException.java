package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * @author Adam Król
 */
public class TeacherNotFoundException extends MzeBaseException {
    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".teacher_not_found";
    }
}
