package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 01.05.15.
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
