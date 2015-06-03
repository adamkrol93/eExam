package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * @author Michał Sośnicki
 */
@UsingDataSet({"ValidUser.yml", "mre/ExamEntityFacadeTest.yml"})
public class ExamEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private ExamEntityFacadeLocal examEntityFacade;

        public void getExamEntityFacadeLocal(Consumer<ExamEntityFacadeLocal> action) {
            action.accept(examEntityFacade);
        }

        public <A> A withExamEntityFacadeLocal(Function<ExamEntityFacadeLocal, A> action) {
            return action.apply(examEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @Test
    public void testReadPresent() {
        Optional<ExamEntity> foundExam = examEntityFacade.findById(1l);

        assertThat("Exam with id = 1", foundExam, is(present()));
        assertThat("Exam's title ", foundExam.get().getTitle(), is("Pewien egzamin 1"));
    }

    @Test
    public void testReadNotPresent() {
        Optional<ExamEntity> foundExam = examEntityFacade.findById(2l);

        assertThat("Exam with id = 2", foundExam, is(not(present())));
    }

    @Test
    public void testFindByDate1() {
        Calendar timestamp = Calendar.getInstance();
        timestamp.set(2015, Calendar.APRIL, 5, 22, 59, 7);

        List<ExamEntity> found = examEntityFacade.findByDate(timestamp);

        assertThat("Exams available at 2015-04-05", found, hasSize(2));
    }

    @Test
    public void testFindByDate2() {
        Calendar timestamp = Calendar.getInstance();
        timestamp.set(2015, Calendar.APRIL, 1, 12, 50, 7);

        List<ExamEntity> found = examEntityFacade.findByDate(timestamp);

        assertThat("Exams available at 2015-04-01", found, hasSize(3));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-ExamEntityFacadeTest#testMerge.yml", excludeColumns = {"exam_version"})
    public void testMerge() {
        ExamEntity exam = mandatoryWrapper.withExamEntityFacadeLocal(e -> e.findById(3l).get());

        exam.setTitle("Zmieniony egzamin 3");
        exam.setDuration(90);

        mandatoryWrapper.getExamEntityFacadeLocal(e -> {
            try {
                e.edit(exam);
            } catch (ApplicationBaseException e1) {
                e1.printStackTrace();
            }
        });
    }

}