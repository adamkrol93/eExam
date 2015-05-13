package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny answer_approach_id tabeli answer
 * @author Andrzej Kurczewski
 */
public class AnswerApproachForeignKeyException extends AnswerManagementException {
    public AnswerApproachForeignKeyException(String message) {
        super(message);
    }

    public AnswerApproachForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".approach_foreign_key";
    }
}
