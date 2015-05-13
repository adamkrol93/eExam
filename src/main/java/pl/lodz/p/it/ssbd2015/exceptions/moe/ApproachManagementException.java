package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizujący problem z podejściem
 * @author Andrzej Kurczewski
 */
public class ApproachManagementException extends MoeBaseException {

    public ApproachManagementException(String message) {
        super(message);
    }

    public ApproachManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".approach";
    }
}
