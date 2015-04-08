package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;

import javax.ejb.EJB;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Andrzej Kurczewski
 */
@UsingDataSet("ValidUser.yml")
public class ExaminerEntityFacadeTest extends BaseArquillianTest {

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