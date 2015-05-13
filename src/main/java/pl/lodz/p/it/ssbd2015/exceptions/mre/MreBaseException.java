package pl.lodz.p.it.ssbd2015.exceptions.mre;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Klasy bazowa dla wyjątków z modułu MRE
 * @author Andrzej Kurczewski
 */
public abstract class MreBaseException extends ApplicationBaseException {
    public MreBaseException(String message) {
        super(message);
    }

    public MreBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".mre";
    }
}
