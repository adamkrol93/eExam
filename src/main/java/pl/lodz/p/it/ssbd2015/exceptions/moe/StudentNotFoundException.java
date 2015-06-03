package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * @author Adam Król
 */
public class StudentNotFoundException extends MoeBaseException {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".student_not_found";
    }
}
