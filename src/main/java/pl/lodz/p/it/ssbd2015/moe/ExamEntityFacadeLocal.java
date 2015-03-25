package pl.lodz.p.it.ssbd2015.moe;

import entities.ExamEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal {
    Optional<ExamEntity> find(Object id);
    List<ExamEntity> findAll();
}
