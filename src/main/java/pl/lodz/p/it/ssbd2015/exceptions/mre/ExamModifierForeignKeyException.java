package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny exam_modifier_id tabeli exam
 * @author Andrzej Kurczewski
 */
public class ExamModifierForeignKeyException extends ExamManagementException {
    public ExamModifierForeignKeyException(String message) {
        super(message);
    }

    public ExamModifierForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".modifier_foreign_key";
    }
}
