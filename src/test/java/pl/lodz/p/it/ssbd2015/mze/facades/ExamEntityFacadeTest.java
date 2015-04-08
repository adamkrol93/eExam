package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static pl.lodz.p.it.ssbd2015.Present.present;


/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * @author Andrzej Kurczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/TeacherEntityFacadeTest.yml"})
public class ExamEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;
    @EJB
    private ExaminerEntityFacadeLocal examinerEntityFacade;

    @Test
    public void shouldFindExistingExamById() throws Exception {
        Optional<ExamEntity> exam = examEntityFacade.findById(1l);

        assertThat("Exam with id = 1", exam, is(present()));
        assertThat("Exam's title ", exam.get().getTitle(), is("Pewien egzamin 1"));
    }

    @Test
    public void shouldNotFindNotExistingExamById() throws Exception {
        Optional<ExamEntity> exam = examEntityFacade.findById(3l);

        assertThat("Exam with id = 3", exam, is(not(present())));
    }

    @Test(expected = EJBException.class)
    public void shouldThrowExceptionBeacuseOfCreatingExamWithNullCreator() {
        ExamEntity exam = TestUtils.makeValidExam(null);
        examEntityFacade.create(exam);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-ExamEntityFacadeTest#shouldSaveNewExamInDatabase.yml")
    public void shouldSaveNewExamInDatabase() {
        ExaminerEntity examiner = examinerEntityFacade.findById(4l).get();

        ExamEntity exam = new ExamEntity();
        exam.setTitle("Brand New Exam");
        exam.setCountTakeExam(7);
        exam.setDateStart(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 2));
        exam.setDateEnd(TestUtils.makeCalendar(2015, 4, 8, 23, 0, 1));
        exam.setDuration(30);
        exam.setCountQuestion(12);
        exam.setCreator(examiner);
        exam.setDateAdd(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 1));

        examEntityFacade.create(exam);
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-ExamEntityFacadeTest#shouldUpdateChangedExamInDatabase.yml")
    public void shouldUpdateChangedExamInDatabase() {
        ExaminerEntity examiner = examinerEntityFacade.findById(4l).get();
        ExamEntity exam = examEntityFacade.findById(1l).get();

        exam.setDuration(40);
        exam.setDateModification(TestUtils.makeCalendar(2015, 4, 8, 22, 10, 1));
        exam.setModifier(examiner);

        examEntityFacade.edit(exam);
    }
}