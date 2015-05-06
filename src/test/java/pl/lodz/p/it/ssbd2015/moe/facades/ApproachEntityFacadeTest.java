package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml","moe/AnswerEntityFacadeTest.yml"})
public class ApproachEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private ApproachEntityFacadeLocal approachEntityFacade;

        public void getApproachEntityFacade(Consumer<ApproachEntityFacadeLocal> action) {
            action.accept(approachEntityFacade);
        }

        public <A> A withApproachEntityFacade(Function<ApproachEntityFacadeLocal, A> action) {
            return action.apply(approachEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @Test
    public void testReadPresent(){
        Optional<ApproachEntity> foundApproach = approachEntityFacade.findById(1l);

        assertThat("Approach with id = 1", foundApproach, is(present()));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-ApproachEntityFacadeTest#testMergeApproach.yml")
    public void testDisqualificationChange(){
        Optional<ApproachEntity> foundApproach = mandatoryWrapper.withApproachEntityFacade(a -> a.findById(1l));
        ApproachEntity approachEntity = foundApproach.get();

        approachEntity.setDisqualification(true);

        mandatoryWrapper.getApproachEntityFacade(a -> {
            try {
                a.edit(approachEntity);
            } catch (ApplicationBaseException e) {
                e.printStackTrace();
            }
        });

    }
}