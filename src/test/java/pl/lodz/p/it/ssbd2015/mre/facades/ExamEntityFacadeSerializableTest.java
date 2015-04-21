package pl.lodz.p.it.ssbd2015.mre.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

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
@UsingDataSet({"ValidUser.yml", "mre/ExamEntityFacadeTest.yml"})
public class ExamEntityFacadeSerializableTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeSerializableTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private ExamEntityFacadeSerializableLocal examEntityFacadeSerializable;

        public void getExamEntityFacadeSerializableLocal(Consumer<ExamEntityFacadeSerializableLocal> action) {
            action.accept(examEntityFacadeSerializable);
        }

        public <A> A withExamEntityFacadeSerializableLocal(Function<ExamEntityFacadeSerializableLocal, A> action) {
            return action.apply(examEntityFacadeSerializable);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private ExamEntityFacadeSerializableLocal examEntityFacadeSerializable;

    @Test
    public void testReadPresent() {
        Optional<ExamEntity> foundExam = examEntityFacadeSerializable.findById(1l);

        assertThat("Exam with id = 1", foundExam, is(present()));
        assertThat("Exam's title ", foundExam.get().getTitle(), is("Pewien egzamin 1"));
    }

    @Test
    public void testReadNotPresent() {
        Optional<ExamEntity> foundExam = examEntityFacadeSerializable.findById(2l);

        assertThat("Exam with id = 2", foundExam, is(not(present())));
    }
}