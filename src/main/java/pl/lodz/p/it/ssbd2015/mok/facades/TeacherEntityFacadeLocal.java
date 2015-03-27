package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface TeacherEntityFacadeLocal {
    void insert(TeacherEntity teacherEntity);
    void update(TeacherEntity teacherEntity);
    Optional<TeacherEntity> find(Object id);
    List<TeacherEntity> findAll();
}
