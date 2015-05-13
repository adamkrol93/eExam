package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny question_modifier_id tabeli question
 * @author Andrzej Kurczewski
 */
public class QuestionModifierForeignKeyException extends QuestionManagementException {
    public QuestionModifierForeignKeyException(String message) {
        super(message);
    }

    public QuestionModifierForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".modifier_foreign_key";
    }
}
