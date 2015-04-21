package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "ValidUser.yml","moe/GuardianUserTest.yml"})
public class GuardianEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

        public void getGuardianEntityFacadeLocal(Consumer<GuardianEntityFacadeLocal> action) {
            action.accept(guardianEntityFacadeLocal);
        }

        public <A> A withGuardianEntityFacadeLocal(Function<GuardianEntityFacadeLocal, A> action) {
            return action.apply(guardianEntityFacadeLocal);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

    @Test
    public void testFindAll() {
        List<GuardianEntity> guardianEntities = guardianEntityFacadeLocal.findAll();

        assertThat("findAll Guardians = 2", guardianEntities, hasSize(2));
    }

}