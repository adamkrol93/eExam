package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * @author Adam Król
 */
public class GuardianNotFoundException extends MoeBaseException{
    public GuardianNotFoundException(String message) {
        super(message);
    }

    public GuardianNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".guardian_not_found";
    }
}
