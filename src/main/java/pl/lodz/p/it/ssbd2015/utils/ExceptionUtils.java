package pl.lodz.p.it.ssbd2015.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * Klasa zawierająca metody pomocne w operowaniu na wyjątkach.
 * @author Michał Sośnicki
 */
public class ExceptionUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

    /**
     * Metoda wywołuje podane operacje, zwracając ich rezultat jeżeli wszystko przebiegnie bez błędów
     * lub zwracając null, jezeli któraś z wykorzystanych wartości jest nullem i wywoła NullPointerException.
     * @param supplier Operacje do wykonania.
     * @param <T> Typ zwracany przez wyrażenie w argumencie i tym samym typ zwracany przez tą metodę.
     * @return Wartość zwrócona przez opakowane wyrażenie.
     */
    public static <T> T elvis(Supplier<T> supplier) {
        try {
            return supplier.get();
        }
        catch (NullPointerException ex) {
            logger.warn("Exception was thrown in elvis wrapper.", ex);
            return null;
        }
    }

}
