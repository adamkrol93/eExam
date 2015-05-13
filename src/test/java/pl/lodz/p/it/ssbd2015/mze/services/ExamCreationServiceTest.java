package pl.lodz.p.it.ssbd2015.mze.services;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.EJB;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static pl.lodz.p.it.ssbd2015.TestUtils.makeCalendar;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@UsingDataSet({"ValidUser.yml", "mze/ExamCreationServiceTest.yml"})
public class ExamCreationServiceTest extends BaseArquillianTest {

    @EJB
    private ExamCreationServiceRemote examCreationService;

    @Test
    public void shouldFindAllQuestions() throws Exception {
        List<QuestionEntity> questions = examCreationService.findAllQuestions();

        assertThat("Service returned all questions in database.", questions, hasSize(5));
    }

    @Test
    public void shouldFindAllTeachers() throws Exception {
        List<TeacherEntity> teachers = examCreationService.findAllTeachers();

        assertThat("Service returned all teachers in database.", teachers, hasSize(3));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "mze/expected-ExamCreationServiceTest#shouldCreateAnExam.yml", excludeColumns = "exam_date_add")
    public void shouldCreateAnExam() throws Exception {
        List<QuestionEntity> questions = examCreationService.findAllQuestions();
        List<TeacherEntity> teachers = examCreationService.findAllTeachers();

        ExamEntity exam = new ExamEntity();
        exam.setTitle("Nowiuteńki egżam");
        exam.setCountTakeExam(4);
        exam.setCountQuestion(13);
        exam.setDateStart(makeCalendar(2015, 10, 7, 21, 30, 5));
        exam.setDateEnd(makeCalendar(2015, 10, 7, 23, 15, 5));
        exam.setDuration(35);

        List<Long> questionIds = Arrays.asList(2l, 4l);
        List<Long> teacherIds = Arrays.asList(6l);

        examCreationService.create(exam, questionIds, teacherIds);
    }

}