package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * wyjątek sygnalizujący brak szukanego Studenta w bazie
 * @author Adam Król
 */
public class StudentNotFoundException extends StudentManagementException {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
