package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.EJB;

import java.util.Optional;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "moe/TeacherUserTest.yml"})
public class TeacherEntityFacadeTest extends BaseArquillianTest{

    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @Test
    public void testFindByLogin() {
        Optional<TeacherEntity> teacherEntityOptional = teacherEntityFacade.findByLogin("nauczyciel");

        assertThat("where login is nauczyciel",teacherEntityOptional,is(present()));
        assertThat("is TeacherClass",teacherEntityOptional,is(instanceOf(TeacherEntity.class)));
    }
}