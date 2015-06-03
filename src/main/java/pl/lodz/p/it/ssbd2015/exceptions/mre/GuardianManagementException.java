package pl.lodz.p.it.ssbd2015.exceptions.mre;

/**
 * Wyjątek oznaczający ogólnie pewien problem z opiekunem.
 * @author Michał Sośnicki
 */
public class GuardianManagementException extends MreBaseException {

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
