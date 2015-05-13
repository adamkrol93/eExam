package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny approach_entrant_id tabeli approach
 * @author Andrzej Kurczewski
 */
public class ApproachEntrantForeignKeyException extends ApproachManagementException {
    public ApproachEntrantForeignKeyException(String message) {
        super(message);
    }

    public ApproachEntrantForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".entrant_foreign_key";
    }
}
