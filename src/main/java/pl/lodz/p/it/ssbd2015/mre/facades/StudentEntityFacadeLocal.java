package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface StudentEntityFacadeLocal {
    Optional<StudentEntity> find(Object id);
    List<StudentEntity> findAll();
}
