package pl.lodz.p.it.ssbd2015.mze;

import entities.ExamEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal {
    void insert(ExamEntity examEntity);
    void update(ExamEntity examEntity);
    Optional<ExamEntity> find(Object id);
    List<ExamEntity> findAll();
}
