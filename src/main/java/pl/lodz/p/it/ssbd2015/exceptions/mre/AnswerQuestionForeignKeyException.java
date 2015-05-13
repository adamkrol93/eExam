package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny answer_question_id tabeli answer
 * @author Andrzej Kurczewski
 */
public class AnswerQuestionForeignKeyException extends AnswerManagementException {
    public AnswerQuestionForeignKeyException(String message) {
        super(message);
    }

    public AnswerQuestionForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".question_foreign_key";
    }
}
