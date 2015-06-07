package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek sygnalizuje próbę ocenienia podejścia które zostało zdyskwalifikowane.
 * Created by Tobiasz Kowalski.
 */
public class ApproachDisqualifiedException extends  ApproachManagementException{

    public ApproachDisqualifiedException(String message){
        super(message);
    }

    public ApproachDisqualifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".disqualified";
    }
}
