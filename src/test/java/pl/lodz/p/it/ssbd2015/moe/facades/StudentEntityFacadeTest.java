package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "ValidUser.yml","moe/StudentUserTest.yml"})
public class StudentEntityFacadeTest extends BaseArquillianTest {

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private StudentEntityFacadeLocal studentEntityFacadeLocal;
        @EJB
        private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

        public void getStudentEntityFacadeLocal(Consumer<StudentEntityFacadeLocal> action) {
            action.accept(studentEntityFacadeLocal);
        }

        public <A> A withStudentEntityFacadeLocal(Function<StudentEntityFacadeLocal, A> action) {
            return action.apply(studentEntityFacadeLocal);
        }

        public void getGuardianEntityFacadeLocal(Consumer<GuardianEntityFacadeLocal> action) {
            action.accept(guardianEntityFacadeLocal);
        }

        public <A> A withGuardianEntityFacadeLocal(Function<GuardianEntityFacadeLocal, A> action) {
            return action.apply(guardianEntityFacadeLocal);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private StudentEntityFacadeLocal studentEntityFacadeLocal;
    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

    @Test
    public void testfindAll() {
        List<StudentEntity> studentEntities = studentEntityFacadeLocal.findAll();

        assertThat("findAll Students = 2",studentEntities,hasSize(2));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-StudentEntityFacadeTest#testMergeStudent.yml")
    public void testEdit() {
        Optional<StudentEntity> studentEntity = mandatoryWrapper.withStudentEntityFacadeLocal(s -> s.findById(6l));
        Optional<GuardianEntity> guardianEntity = mandatoryWrapper.withGuardianEntityFacadeLocal(g -> g.findById(2l));

        StudentEntity studentEntity1 = studentEntity.get();
        GuardianEntity guardianEntity1 = guardianEntity.get();

        studentEntity1.setGuardian(guardianEntity1);

        mandatoryWrapper.getStudentEntityFacadeLocal(s -> {
            try {
                s.edit(studentEntity1);
            } catch (ApplicationBaseException e) {
                e.printStackTrace();
            }
        });
    }
}