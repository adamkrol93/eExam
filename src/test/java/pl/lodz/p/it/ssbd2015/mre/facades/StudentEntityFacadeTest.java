package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Michał Sośnicki
 */
@UsingDataSet({"ValidUser.yml", "mre/AnswerEntityFacadeTest.yml"})
public class StudentEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private StudentEntityFacadeLocal studentEntityFacade;

        public void getStudentEntityFacadeLocal(Consumer<StudentEntityFacadeLocal> action) {
            action.accept(studentEntityFacade);
        }

        public <A> A withStudentEntityFacadeLocal(Function<StudentEntityFacadeLocal, A> action) {
            return action.apply(studentEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;

    @Test
    public void testFindApproaches1() {
        StudentEntity student = studentEntityFacade.findById(6l).get();

        assertThat("Student's {id = 6} approaches", student.getEntered(), hasSize(3));
    }

    @Test
    public void testFindApproaches2() {
        StudentEntity student = studentEntityFacade.findById(7l).get();

        assertThat("Student's {id = 7} approaches", student.getEntered(), hasSize(1));
    }

}