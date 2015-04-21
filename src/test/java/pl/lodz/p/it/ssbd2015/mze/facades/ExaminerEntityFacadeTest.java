package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Andrzej Kurczewski
 */
@UsingDataSet("ValidUser.yml")
public class ExaminerEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private ExaminerEntityFacadeLocal examinerEntityFacade;

        public void getExaminerEntityFacadeLocal(Consumer<ExaminerEntityFacadeLocal> action) {
            action.accept(examinerEntityFacade);
        }

        public <A> A withExaminerEntityFacadeLocal(Function<ExaminerEntityFacadeLocal, A> action) {
            return action.apply(examinerEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private ExaminerEntityFacadeLocal examinerEntityFacade;

    @Test
    public void shouldFindExistingExaminerByLogin() throws Exception {
        Optional<ExaminerEntity> user = examinerEntityFacade.findByLogin("osoba");

        assertThat("Examiner with login = osoba", user, is(present()));
        assertThat("Examiner's email ", user.get().getEmail(), is("nie@ma.pl"));
    }

    @Test
    public void shouldNotFindNotExistingExaminerByLogin() throws Exception {
        Optional<ExaminerEntity> user = examinerEntityFacade.findByLogin("nieosoba");

        assertThat("Examiner with login = nieosoba", user, is(not(present())));
    }
}