package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.*;

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
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mre/AnswerEntityFacadeTest.yml"})
public class ApproachEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private StudentEntityFacadeLocal studentEntityFacade;
        @EJB
        private ExamEntityFacadeLocal examEntityFacade;
        @EJB
        private ApproachEntityFacadeLocal approachEntityFacade;

        public void getStudentEntityFacadeLocal(Consumer<StudentEntityFacadeLocal> action) {
            action.accept(studentEntityFacade);
        }

        public <A> A withStudentEntityFacadeLocal(Function<StudentEntityFacadeLocal, A> action) {
            return action.apply(studentEntityFacade);
        }

        public void getExamEntityFacadeLocal(Consumer<ExamEntityFacadeLocal> action) {
            action.accept(examEntityFacade);
        }

        public <A> A withExamEntityFacadeLocal(Function<ExamEntityFacadeLocal, A> action) {
            return action.apply(examEntityFacade);
        }

        public void getApproachEntityFacadeLocal(Consumer<ApproachEntityFacadeLocal> action) {
            action.accept(approachEntityFacade);
        }

        public <A> A withApproachEntityFacadeLocal(Function<ApproachEntityFacadeLocal, A> action) {
            return action.apply(approachEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;
    @EJB
    private ExamEntityFacadeLocal examEntityFacade;
    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @Test
    public void testReadPresent() {
        Optional<ApproachEntity> foundApproach = approachEntityFacade.findById(1l);

        assertThat("approach with id = 1", foundApproach, is(present()));
        assertThat("approach's {id = 1} exam title", foundApproach.get().getExam().getTitle(), is("Pewien egzamin 1"));
    }

    @Test
    public void testReadNotPresent() {
        Optional<ApproachEntity> foundApproach = approachEntityFacade.findById(7l);

        assertThat("approach with id = 7", foundApproach, is(not(present())));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-ApproachEntityFacadeTest#testCreate.yml")
    public void testCreate() {
        ExamEntity exam = mandatoryWrapper.withExamEntityFacadeLocal(e -> e.findById(1l).get());
        StudentEntity student = mandatoryWrapper.withStudentEntityFacadeLocal(s -> s.findById(7l).get());

        ApproachEntity approach = new ApproachEntity();
        approach.setDateStart(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 2));
        approach.setDateEnd(TestUtils.makeCalendar(2015, 4, 8, 23, 0, 1));
        approach.setExam(exam);
        approach.setEntrant(student);
        approach.setDateAdd(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 1));

        mandatoryWrapper.getApproachEntityFacadeLocal(a -> a.create(approach));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-ApproachEntityFacadeTest#testCreateWithAnswers.yml",excludeColumns = "answer_id")
    public void testCreateWithAnswers() {
        ExamEntity exam = mandatoryWrapper.withExamEntityFacadeLocal(e -> e.findById(1l).get());
        StudentEntity student = mandatoryWrapper.withStudentEntityFacadeLocal(s -> s.findById(7l).get());

        ApproachEntity approach = new ApproachEntity();
        approach.setDateStart(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 2));
        approach.setDateEnd(TestUtils.makeCalendar(2015, 4, 8, 23, 0, 1));
        approach.setExam(exam);
        approach.setEntrant(student);
        approach.setDateAdd(TestUtils.makeCalendar(2015, 4, 8, 22, 0, 1));

        for (QuestionEntity question : exam.getQuestions()) {
            AnswerEntity answer = new AnswerEntity();
            answer.setApproach(approach);
            answer.setContent("");
            answer.setDateAdd(TestUtils.makeCalendar(2015, 4, 7, 17, 0, 7));
            answer.setGrade(0);
            answer.setQuestion(question);
            approach.getAnswers().add(answer);
        }

        mandatoryWrapper.getApproachEntityFacadeLocal(a -> a.create(approach));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mre/expected-ApproachEntityFacadeTest#testMergeDisqualify.yml")
    public void testMergeDisqualify() {
        ApproachEntity approach = mandatoryWrapper.withApproachEntityFacadeLocal(a -> a.findById(4l).get());

        approach.setDisqualification(true);

        mandatoryWrapper.getApproachEntityFacadeLocal(a -> a.edit(approach));
    }

}