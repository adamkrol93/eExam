package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny question_creator_id tabeli question
 * @author Andrzej Kurczewski
 */
public class QuestionCreatorForeignKeyException extends QuestionManagementException {
    public QuestionCreatorForeignKeyException(String message) {
        super(message);
    }

    public QuestionCreatorForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".creator_foreign_key";
    }
}
