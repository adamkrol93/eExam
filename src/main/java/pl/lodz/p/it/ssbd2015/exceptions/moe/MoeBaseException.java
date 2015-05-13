package pl.lodz.p.it.ssbd2015.exceptions.moe;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Klasy bazowa dla wyjątków z modułu MOE
 * @author Andrzej Kurczewski
 */
public abstract class MoeBaseException extends ApplicationBaseException {
    public MoeBaseException(String message) {
        super(message);
    }

    public MoeBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".moe";
    }
}
