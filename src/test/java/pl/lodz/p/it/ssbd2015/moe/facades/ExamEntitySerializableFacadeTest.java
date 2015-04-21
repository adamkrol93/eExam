package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
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
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml","moe/ExamEntityFacadeTest.yml"})
public class ExamEntitySerializableFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ExamEntitySerializableFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private ExamEntitySerializableFacadeLocal examEntitySerializableFacade;

        public void getExamEntitySerializableFacadeLocal(Consumer<ExamEntitySerializableFacadeLocal> action) {
            action.accept(examEntitySerializableFacade);
        }

        public <A> A withExamEntitySerializableFacadeLocal(Function<ExamEntitySerializableFacadeLocal, A> action) {
            return action.apply(examEntitySerializableFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private ExamEntitySerializableFacadeLocal examEntitySerializableFacade;

    @Test
    public void testReadPresent(){
        Optional<ExamEntity> foundExam = examEntitySerializableFacade.findById(1l);

        assertThat("exam with id = 1",foundExam, is(present()));
    }


    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-ExamEntityFacadeTest#testMergeExam.yml")
    public void testDurationChange(){
        Optional<ExamEntity> foundExam = mandatoryWrapper.withExamEntitySerializableFacadeLocal(e -> e.findById(1l));
        ExamEntity examEntity = foundExam.get();

        examEntity.setDuration(20);

        mandatoryWrapper.getExamEntitySerializableFacadeLocal(e -> e.edit(examEntity));
    }

}