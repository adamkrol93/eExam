package pl.lodz.p.it.ssbd2015.mok.localization;

import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.hibernate.validator.spi.resourceloading.ResourceBundleLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Modyfikacja klasy {@link org.hibernate.validator.resourceloading.PlatformResourceBundleLocator} pozwalająca obsługiwać pliki properties kodowane w UTF-8
 */
public class UTF8ResourceBundleLocator implements ResourceBundleLocator {
    protected static final String BUNDLE_EXTENSION = "properties";
    protected static final ResourceBundle.Control UTF8_CONTROL = new UTF8Control(BUNDLE_EXTENSION);
    private static final Logger logger = LoggerFactory.getLogger(UTF8ResourceBundleLocator.class);
    private final String bundleName;

    /**
     * Tworzy nową instancję lokalizatora bundli,
     * pozwalającą obsługiwać pliki properties kodowane w UTF-8
     * @param bundleName nazwa pliku properties, która jest wyszukiwana
     */
    public UTF8ResourceBundleLocator(String bundleName) {
        this.bundleName = bundleName;
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
        ResourceBundle rb = null;
        ClassLoader classLoader = GetClassLoader.fromContext();
        if (classLoader != null) {
            rb = loadBundle(classLoader, locale, bundleName + " not found by thread local classloader");
        }
        if (rb == null) {
            classLoader = GetClassLoader.fromClass(PlatformResourceBundleLocator.class);
            rb = loadBundle(classLoader, locale, bundleName + " not found by validator classloader");
        }

        return rb;
    }

    /**
     * Kopia metody loadBundle z {@link PlatformResourceBundleLocator}, która dodatkowo wskazuje wykorzystanie
     * kodowania UTF-8 do wczytywania, podając obiekt klasy {@link UTF8Control}
     * @param classLoader wykorzystywany ClassLoader
     * @param locale obiekt wskazujący poszukiwaną wersję językową
     * @param message wiadomość do wyświetlenia w razie niepowodzenia
     * @return wczytane bundle
     */
    private ResourceBundle loadBundle(ClassLoader classLoader, Locale locale, String message) {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle(bundleName, locale, classLoader, UTF8_CONTROL);
        } catch (MissingResourceException ignored) {
            logger.trace(message);
        }
        return rb;
    }

    private static class GetClassLoader implements PrivilegedAction<ClassLoader> {
        private final Class<?> clazz;

        private GetClassLoader(Class<?> clazz) {
            this.clazz = clazz;
        }

        private static ClassLoader fromContext() {
            final GetClassLoader action = new GetClassLoader(null);
            if (System.getSecurityManager() != null) {
                return AccessController.doPrivileged(action);
            } else {
                return action.run();
            }
        }

        @Override
        public ClassLoader run() {
            if (clazz != null) {
                return clazz.getClassLoader();
            } else {
                return Thread.currentThread().getContextClassLoader();
            }
        }

        private static ClassLoader fromClass(Class<?> clazz) {
            if (clazz == null) {
                throw new IllegalArgumentException("Class is null");
            }
            final GetClassLoader action = new GetClassLoader(clazz);
            if (System.getSecurityManager() != null) {
                return AccessController.doPrivileged(action);
            } else {
                return action.run();
            }
        }
    }
}