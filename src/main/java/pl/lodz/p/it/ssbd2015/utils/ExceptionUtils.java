package pl.lodz.p.it.ssbd2015.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class ExceptionUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

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
