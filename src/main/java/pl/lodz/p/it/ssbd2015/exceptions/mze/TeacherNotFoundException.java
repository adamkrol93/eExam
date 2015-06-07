package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący, że w bazie danych nie ma szukanego nauczyciela
 * @author Adam Król
 */
public class TeacherNotFoundException extends TeacherManagementException {
    public TeacherNotFoundException(String message) {
        super(message);
    }

    public TeacherNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
