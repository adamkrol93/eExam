package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący brak wyszukiwanego egzaminu w bazie danych
 * @author Andrzej Kurczewski
 */
public class ExamNotFoundException extends ExamManagementException {
    public ExamNotFoundException(String message) {
        super(message);
    }

    public ExamNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
