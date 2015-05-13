package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący brak wyszukiwanego pytania w bazie danych
 * @author Andrzej Kurczewski
 */
public class QuestionNotFoundException extends QuestionManagementException {
    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".not_found";
    }
}
