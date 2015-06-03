package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;

import java.io.File;
import java.net.URL;

/**
 * @author Michał Sośnicki
 */
@RunWith(Arquillian.class)
public abstract class BaseFunctionalTest {

    @Deployment(testable = false)
    public static WebArchive createArchive() {
        return BaseArquillianTest.createArchive()
                .addPackages(true, "org.slf4j")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"),
                        "faces-config.xml")
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                                .importDirectory("src/main/webapp").as(GenericArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                                .importDirectory("src/main/webapp/resources").as(GenericArchive.class),
                        "/resources/", Filters.includeAll());
    }

    @Drone
    protected WebDriver browser;

    @ArquillianResource
    protected URL deploymentUrl;

}
