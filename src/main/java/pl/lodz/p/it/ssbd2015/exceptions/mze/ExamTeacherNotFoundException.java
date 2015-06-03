package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * @author Adam Król
 */
public class ExamTeacherNotFoundException extends ExamManagementException {
    public ExamTeacherNotFoundException(String message) {
        super(message);
    }

    public ExamTeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".teacher_not_found";
    }
}
