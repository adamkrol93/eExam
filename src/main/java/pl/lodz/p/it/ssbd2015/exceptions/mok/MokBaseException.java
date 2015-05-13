package pl.lodz.p.it.ssbd2015.exceptions.mok;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Klasy bazowa dla wyjątków z modułu MOK
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public abstract class MokBaseException extends ApplicationBaseException {
    public MokBaseException(String message) {
        super(message);
    }

    public MokBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".mok";
    }
}
