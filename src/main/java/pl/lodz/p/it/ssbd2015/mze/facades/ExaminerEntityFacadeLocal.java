package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExaminerEntityFacadeLocal {
    Optional<ExaminerEntity> find(Object id);
    List<ExaminerEntity> findAll();
}
