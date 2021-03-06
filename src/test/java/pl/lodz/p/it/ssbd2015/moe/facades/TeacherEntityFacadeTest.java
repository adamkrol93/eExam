package pl.lodz.p.it.ssbd2015.moe.facades;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.junit.Test;
import pl.lodz.p.it.ssbd2015.BaseArquillianTest;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static pl.lodz.p.it.ssbd2015.Present.present;

/**
 * Created by adam on 08.04.15.
 */
@UsingDataSet({ "ValidUser.yml","moe/TeacherUserTest.yml"})
public class TeacherEntityFacadeTest extends BaseArquillianTest{

    @Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeTest.MandatoryWrapper")
    @LocalBean
    public static class MandatoryWrapper {
        @EJB
        private TeacherEntityFacadeLocal teacherEntityFacade;

        public void getTeacherEntityFacadeLocal(Consumer<TeacherEntityFacadeLocal> action) {
            action.accept(teacherEntityFacade);
        }

        public <A> A withTeacherEntityFacadeLocal(Function<TeacherEntityFacadeLocal, A> action) {
            return action.apply(teacherEntityFacade);
        }
    }

    @EJB
    private MandatoryWrapper mandatoryWrapper;
    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @Test
    public void testFindByLogin() {
        Optional<TeacherEntity> teacherEntityOptional = teacherEntityFacade.findByLogin("nauczyciel");

        assertThat("where login is nauczyciel",teacherEntityOptional,is(present()));
        assertThat("is TeacherClass",teacherEntityOptional.get(),is(instanceOf(TeacherEntity.class)));
    }

    @Test
    public void testFindByLoginNotPresent() {
        Optional<TeacherEntity> teacherEntityOptional = teacherEntityFacade.findByLogin("nieMaMnie");

        assertThat("Teacher where login is nieMaMnie", teacherEntityOptional, is(not(present())));
    }
}