package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek, w założeniu ma opakowywać IllegalArgumentException rzucany przez EntityManager.merge,
 * jeżeli otrzymał obiekt, który nie jest encją lub obiekt encji w stanie usuniętym
 * @author Andrzej Kurczewski
 */
public class ApproachIllegalArgumentException extends ApproachManagementException {
    public ApproachIllegalArgumentException(String message) {
        super(message);
    }

    public ApproachIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
