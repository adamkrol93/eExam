package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny exam_creator_id tabeli exam
 * @author Andrzej Kurczewski
 */
public class ExamCreatorForeignKeyException extends ExamManagementException {
    public ExamCreatorForeignKeyException(String message) {
        super(message);
    }

    public ExamCreatorForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".creator_foreign_key";
    }
}
