package pl.lodz.p.it.ssbd2015.mok;

import entities.PreviousPasswordEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface PreviousPassEntityFacadeLocal {
    void insert(PreviousPasswordEntity previousPasswordEntity);
    void update(PreviousPasswordEntity previousPasswordEntity);
    Optional<PreviousPasswordEntity> find(Object id);
    List<PreviousPasswordEntity> findAll();
}
