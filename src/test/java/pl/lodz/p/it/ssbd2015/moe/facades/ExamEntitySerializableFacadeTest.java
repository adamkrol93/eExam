package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.EJB;

import java.util.Optional;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml","moe/ExamEntityFacadeTest.yml"})
public class ExamEntitySerializableFacadeTest extends BaseArquillianTest {

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

        Optional<ExamEntity> foundExam = examEntitySerializableFacade.findById(1l);
        ExamEntity examEntity = foundExam.get();

        examEntity.setDuration(20);

        examEntitySerializableFacade.edit(examEntity);
    }

}