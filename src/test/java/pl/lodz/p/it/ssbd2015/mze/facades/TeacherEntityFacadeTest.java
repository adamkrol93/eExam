package pl.lodz.p.it.ssbd2015.mze.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.EJB;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Andrzej Kurczewski
 */
@UsingDataSet({"ValidUser.yml", "mze/TeacherEntityFacadeTest.yml"})
public class TeacherEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;
    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @Test
    public void shouldReturnListWithOneTeacherWhoIsNotConnectedWithExam() throws Exception {
        ExamEntity exam = examEntityFacade.findById(2l).get();
        List<TeacherEntity> teachers = teacherEntityFacade.findAllNotInExam(exam);

        for (TeacherEntity teacher : teachers) {
            System.out.println(teacher.getId());
        }

        TeacherEntity teacher = teacherEntityFacade.findById(7l).get();

        assertThat("List of teachers", teachers, is(not(empty())));
        assertThat("List of teachers", teachers, hasSize(1));
        assertThat("List of teachers", teachers, hasItem(hasProperty("id", equalTo(teacher.getId()))));
    }

    @Test
    public void shouldReturnEmptyListSearchingForTeachersNotInExamConnectedWithAllTeachers() throws Exception {
        ExamEntity exam = examEntityFacade.findById(1l).get();
        List<TeacherEntity> teachers = teacherEntityFacade.findAllNotInExam(exam);

        assertThat("List of teachers", teachers, is(empty()));
    }
}