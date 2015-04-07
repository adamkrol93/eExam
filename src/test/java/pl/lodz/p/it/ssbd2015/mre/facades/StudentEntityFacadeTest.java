package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.EJB;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mre/AnswerEntityFacadeTest.yml"})
public class StudentEntityFacadeTest extends BaseArquillianTest {

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