package pl.lodz.p.it.ssbd2015.mok;

import entities.ExaminerEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExaminerEntityFacadeLocal {
    void insert(ExaminerEntity examinerEntity);
    void update(ExaminerEntity examinerEntity);
    Optional<ExaminerEntity> find(Object id);
    List<ExaminerEntity> findAll();
}
