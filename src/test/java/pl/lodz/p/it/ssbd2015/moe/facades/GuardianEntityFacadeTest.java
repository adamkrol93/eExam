package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.EJB;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "ValidUser.yml","moe/GuardianUserTest.yml"})
public class GuardianEntityFacadeTest extends BaseArquillianTest {


    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

    @Test
    public void testfindAll()
    {
        List<GuardianEntity> guardianEntities = guardianEntityFacadeLocal.findAll();

        assertThat("findAll Guardians = 2", guardianEntities, hasSize(2));
    }

}