package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek, w założeniu ma opakowywać IllegalArgumentException rzucany przez EntityManager.merge,
 * jeżeli otrzymał obiekt, który nie jest encją lub obiekt encji w stanie usuniętym
 * @author Andrzej Kurczewski
 */
public class QuestionIllegalArgumentException extends QuestionManagementException {
    public QuestionIllegalArgumentException(String message) {
        super(message);
    }

    public QuestionIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
