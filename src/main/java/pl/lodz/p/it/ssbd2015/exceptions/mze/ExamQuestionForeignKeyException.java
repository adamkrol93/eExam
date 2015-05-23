package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla tabeli exam_questions
 * @author Michał Sośnicki
 */
public class ExamQuestionForeignKeyException extends ExamManagementException {

    public ExamQuestionForeignKeyException(String message) {
        super(message);
    }

    public ExamQuestionForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exam_question_foreign_key";
    }
}
