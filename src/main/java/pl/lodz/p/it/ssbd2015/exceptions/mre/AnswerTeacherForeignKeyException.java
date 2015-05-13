package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny answer_teacher_id tabeli answer
 * @author Andrzej Kurczewski
 */
public class AnswerTeacherForeignKeyException extends AnswerManagementException {
    public AnswerTeacherForeignKeyException(String message) {
        super(message);
    }

    public AnswerTeacherForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".teacher_foreign_key";
    }
}
