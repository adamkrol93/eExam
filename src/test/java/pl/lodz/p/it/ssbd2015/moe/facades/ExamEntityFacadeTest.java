package pl.lodz.p.it.ssbd2015.moe.facades;

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
public class ExamEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ExamEntitySerializableFacadeTest.MandatoryWrapper")
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
    public void testReadPresent(){
        Optional<ExamEntity> foundExam = examEntityFacade.findById(1l);

        assertThat("exam with id = 1",foundExam, is(present()));
    }


    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-ExamEntityFacadeTest#testMergeExam.yml")
    public void testDurationChange(){
        Optional<ExamEntity> foundExam = mandatoryWrapper.withExamEntityFacadeLocal(e -> e.findById(1l));
        ExamEntity examEntity = foundExam.get();

        examEntity.setDuration(20);

        mandatoryWrapper.getExamEntityFacadeLocal(e -> {
            try {
                e.edit(examEntity);
            } catch (ApplicationBaseException e1) {
                e1.printStackTrace();
            }
        });
    }

}