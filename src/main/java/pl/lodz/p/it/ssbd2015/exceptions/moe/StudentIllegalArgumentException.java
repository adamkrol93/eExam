package pl.lodz.p.it.ssbd2015.exceptions.moe;

/**
 * Wyjątek, w założeniu ma opakowywać IllegalArgumentException rzucany przez EntityManager.merge,
 * jeżeli otrzymał obiekt, który nie jest encją lub obiekt encji w stanie usuniętym
 * @author Andrzej Kurczewski
 */
public class StudentIllegalArgumentException extends StudentManagementException {
    public StudentIllegalArgumentException(String message) {
        super(message);
    }

    public StudentIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".illegal_argument";
    }
}
