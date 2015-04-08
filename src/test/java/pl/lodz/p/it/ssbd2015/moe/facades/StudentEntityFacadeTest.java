package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.EJB;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "ValidUser.yml","moe/StudentUserTest.yml"})
public class StudentEntityFacadeTest extends BaseArquillianTest {


    @EJB
    private StudentEntityFacadeLocal studentEntityFacadeLocal;

    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacadeLocal;

    @Test
    public void testfindAll()
    {
        List<StudentEntity> studentEntities = studentEntityFacadeLocal.findAll();

        assertThat("findAll Students = 2",studentEntities,hasSize(2));
    }

    @Test
    @Transactional(TransactionMode.DISABLED)
    @ShouldMatchDataSet(value = "moe/expected-StudentEntityFacadeTest#testMergeStudent.yml")
    public void testEdit()
    {
        Optional<StudentEntity> studentEntity = studentEntityFacadeLocal.findById(6l);
        Optional<GuardianEntity> guardianEntity = guardianEntityFacadeLocal.findById(2l);

        StudentEntity studentEntity1 = studentEntity.get();
        GuardianEntity guardianEntity1 = guardianEntity.get();

        studentEntity1.setGuardian(guardianEntity1);

        studentEntityFacadeLocal.edit(studentEntity1);
    }
}