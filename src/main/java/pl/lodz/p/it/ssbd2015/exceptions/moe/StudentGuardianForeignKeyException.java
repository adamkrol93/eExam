package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla kolumny groups_guardian tabeli groups
 * @author Andrzej Kurczewski
 */
public class StudentGuardianForeignKeyException extends StudentManagementException {
    public StudentGuardianForeignKeyException(String message) {
        super(message);
    }

    public StudentGuardianForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".guardian_foreign_key";
    }
}
