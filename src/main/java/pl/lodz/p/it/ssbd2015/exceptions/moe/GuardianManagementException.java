package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący problem z zarządzaniem Opiekunami
 * @author Adam Król
 */
public class GuardianManagementException extends MoeBaseException{
    public GuardianManagementException(String message) {
        super(message);
    }

    public GuardianManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".guardian";
    }
}
