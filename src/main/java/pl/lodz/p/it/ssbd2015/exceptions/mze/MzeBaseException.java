package pl.lodz.p.it.ssbd2015.exceptions.mze;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Klasy bazowa dla wyjątków z modułu MRE
 * @author Andrzej Kurczewski
 */
public abstract class MzeBaseException extends ApplicationBaseException {
    public MzeBaseException(String message) {
        super(message);
    }

    public MzeBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".mze";
    }
}
