package pl.lodz.p.it.ssbd2015;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.FileAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import java.io.File;

/**
 * Klasa testów deployowanych na uruchomionej wcześniej instancji GlassFisha, korzystających z przygotowanej
 * wcześniej bazy ssbd01test. Wydaje mi się, że to podejście jest szybsze i pewniejsze, niż wersja embedded.
 * Paczka z testami sama przygotuje sobie odpowiednie pule połączeń i zasoby JDBC i zwolni je po wszystkim.
 * Można użyć adnotacja Transactional, by objąć cały test transakcją, jednak przy Rollbacku zmiany nie są nigdy
 * utrwalane w bazie, co może sprawić, iż nie zostanie wykryty błąd w integracji bazy z aplikacją.
 * Póki co nie można mieć żadnej pewności co do stanu bazy na początku wykonania testu.
 * @see org.jboss.arquillian.transaction.api.annotation.Transactional
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@RunWith(Arquillian.class)
public abstract class BaseArquillianTest {

    @Deployment
    public static WebArchive createArchive() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "pl.lodz.p.it.ssbd2015")
                .addAsWebInfResource(new FileAsset(new File("src/main/webapp/WEB-INF/beans.xml")),
                        "beans.xml")
                .addAsWebInfResource(new FileAsset(new File("src/test/resources/glassfish-resources.xml")),
                        "glassfish-resources.xml")
                .addAsWebInfResource(new FileAsset(new File("src/main/resources/META-INF/persistence.xml")),
                        "classes/META-INF/persistence.xml");
    }

}
