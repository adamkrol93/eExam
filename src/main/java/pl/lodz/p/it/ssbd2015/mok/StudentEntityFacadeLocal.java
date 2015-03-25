package pl.lodz.p.it.ssbd2015.mok;

import entities.StudentEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface StudentEntityFacadeLocal {
    void insert(StudentEntity studentEntity);
    void update(StudentEntity studentEntity);
    Optional<StudentEntity> find(Object id);
    List<StudentEntity> findAll();
}
