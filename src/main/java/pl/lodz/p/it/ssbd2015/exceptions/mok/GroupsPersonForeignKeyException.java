package pl.lodz.p.it.ssbd2015.exceptions.mok;

/**
 * Klasa stworzona dla wyjątków związanych z kluczem obcym na tabeli GroupsPerson
 * @author Adam Król
 */
public class GroupsPersonForeignKeyException extends GroupsManagementException {
    public GroupsPersonForeignKeyException(String message) {
        super(message);
    }

    public GroupsPersonForeignKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".person_foreign_key";
    }
}
