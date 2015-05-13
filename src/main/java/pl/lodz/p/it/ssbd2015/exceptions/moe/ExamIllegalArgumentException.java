package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek, w założeniu ma opakowywać IllegalArgumentException rzucany przez EntityManager.merge,
 * jeżeli otrzymał obiekt, który nie jest encją lub obiekt encji w stanie usuniętym
 * @author Andrzej Kurczewski
 */
public class ExamIllegalArgumentException extends ExamManagementException {
    public ExamIllegalArgumentException(String message) {
        super(message);
    }

    public ExamIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
