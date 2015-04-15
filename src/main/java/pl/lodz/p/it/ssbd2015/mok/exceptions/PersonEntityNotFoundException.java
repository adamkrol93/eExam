package pl.lodz.p.it.ssbd2015.mok.exceptions;

/**
 * Created by adam on 15.04.15.
 */
public class PersonEntityNotFoundException extends Exception {
    public PersonEntityNotFoundException() {
        super();
    }

    public PersonEntityNotFoundException(String message) {
        super(message);
    }

    public PersonEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
