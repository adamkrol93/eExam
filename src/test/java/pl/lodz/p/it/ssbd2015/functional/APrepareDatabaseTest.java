package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class APrepareDatabaseTest extends BaseArquillianTest {

    @Test
    @UsingDataSet({"ValidUser.yml", "functional/Generators.yml"})
    public void prepareDatabase() {
    }

}
