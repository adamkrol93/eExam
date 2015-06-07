package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący problem duplikatu tytułu
 * @author Adam Król
 */
public class ExamTitleNotUniqueException extends ExamManagementException{
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
