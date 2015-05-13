package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny approach_exam_id tabeli approach
 * @author Andrzej Kurczewski
 */
public class ApproachExamForeignKeyException extends ApproachManagementException {
    public ApproachExamForeignKeyException(String message) {
        super(message);
    }

    public ApproachExamForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exam_foreign_key";
    }
}
