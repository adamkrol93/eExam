package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący naruszenie ograniczenia klucza obcego dla tabeli exam_groups
 * @author Michał Sośnicki
 */
public class ExamTeacherForeignKeyException extends ExamManagementException {
    public ExamTeacherForeignKeyException(String message) {
        super(message);
    }

    public ExamTeacherForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".exam_teacher_foreign_key";
    }
}
