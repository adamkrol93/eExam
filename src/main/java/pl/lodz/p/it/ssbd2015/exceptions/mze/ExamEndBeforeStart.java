package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący, że data końca egzaminu została ustalona na datę przed datą rozpoczęcia.
 * @author Michał Sośnicki
 */
public class ExamEndBeforeStart extends ExamManagementException {
    public ExamEndBeforeStart(String message) {
        super(message);
    }

    public ExamEndBeforeStart(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".end_before_start";
    }
}
