package pl.lodz.p.it.ssbd2015.mok.localization;

import javax.faces.context.FacesContext;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Zasób udostępniany w kontekście JSF, udostępniający dane do internacjonalizacji wiadomości, lecz
 * z obsługą UTF-8.
 * @author Created by adam on 15.04.15.
 */
public class Text extends ResourceBundle {

    protected static final String BUNDLE_NAME = "i18n.mailer";
    protected static final String BUNDLE_EXTENSION = "properties";
    protected static final Control UTF8_CONTROL = new UTF8Control(BUNDLE_EXTENSION);

    public Text() {
        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context != null ? context.getViewRoot().getLocale() : new Locale("en");
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, locale, UTF8_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }
}
