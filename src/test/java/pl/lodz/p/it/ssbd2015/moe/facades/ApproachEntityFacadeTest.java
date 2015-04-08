package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.EJB;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@UsingDataSet({"ValidUser.yml","moe/AnswerEntityFacadeTest.yml"})
public class ApproachEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @Test
    public void testReadPresent(){
        Optional<ApproachEntity> foundApproach = approachEntityFacade.findById(1l);

        assertThat("Approach with id = 1",foundApproach, is(present()));
    }


    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-ApproachEntityFacadeTest#testMergeApproach.yml")
    public void testDisqualificationChange(){

        Optional<ApproachEntity> foundApproach = approachEntityFacade.findById(1l);
        ApproachEntity approachEntity = foundApproach.get();

        approachEntity.setDisqualification(true);

        approachEntityFacade.edit(approachEntity);
    }
}