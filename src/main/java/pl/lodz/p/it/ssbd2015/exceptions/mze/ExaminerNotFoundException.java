package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący brak wyszukiwanego egzaminatora w bazie danych
 * @author Andrzej Kurczewski
 */
public class ExaminerNotFoundException extends ExaminerManagementException {
    public ExaminerNotFoundException(String message) {
        super(message);
    }

    public ExaminerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
