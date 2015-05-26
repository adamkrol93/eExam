package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący próbę naruszenia unikalności nazwy egzaminu
 * @author Michał Sośnicki
 */
public class ExamTitleNotUniqueException extends ExamManagementException {
    public ExamTitleNotUniqueException(String message) {
        super(message);
    }

    public ExamTitleNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".title_not_unique";
    }
}
