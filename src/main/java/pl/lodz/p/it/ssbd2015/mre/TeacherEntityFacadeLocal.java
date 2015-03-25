package pl.lodz.p.it.ssbd2015.mre;

import entities.TeacherEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface TeacherEntityFacadeLocal {
    Optional<TeacherEntity> find(Object id);
    List<TeacherEntity> findAll();
}
