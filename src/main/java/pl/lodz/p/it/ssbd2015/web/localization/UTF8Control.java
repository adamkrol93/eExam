package pl.lodz.p.it.ssbd2015.web.localization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Modyfikacja klasy rozszerzajacej pozwalająca obsługiwać pliki properties kodowane w UTF-8
 */
public class UTF8Control extends ResourceBundle.Control {
    private String bundleExtension;

    public UTF8Control(String bundleExtension) {
        this.bundleExtension = bundleExtension;
    }

    /**
     * Tworzy bundle,, obsługującego pliki properties z kodowaniem UTF-8
     * @param baseName nazwa podstawowa dla plików properties
     * @param locale język w którym ma działać ta klasa
     * @param format format zwracanych danych
     * @param loader classLoader w którym szukać ma plików properties
     * @param reload zmienna sygnalizująca czy mamy przeładować całą konfigurację
     * @return Bundle do wykonywania operacji internacjonalizowania
     * @throws IllegalAccessException Rzucany, kiedy jest problem z dostępem
     * @throws InstantiationException Rzucany, kiedy jest problem z tworzeniem
     * @throws IOException Rzucany, kiedy jest problem z wejściem/wyjściem
     */
    public ResourceBundle newBundle
        (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
        throws IllegalAccessException, InstantiationException, IOException
    {
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, bundleExtension);
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}
