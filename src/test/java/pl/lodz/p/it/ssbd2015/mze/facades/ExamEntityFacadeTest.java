package pl.lodz.p.it.ssbd2015.mze.facades;

import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.TestUtils;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.EJB;
import javax.ejb.EJBException;


/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class ExamEntityFacadeTest extends BaseArquillianTest {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @Test(expected = EJBException.class)
    public void testCreatorConstraint() {
        ExamEntity exam = TestUtils.makeValidExam(null);
        examEntityFacade.create(exam);
    }

}