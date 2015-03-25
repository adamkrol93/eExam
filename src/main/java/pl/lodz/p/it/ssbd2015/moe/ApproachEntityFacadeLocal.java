package pl.lodz.p.it.ssbd2015.moe;

import entities.ApproachEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ApproachEntityFacadeLocal {
    void update(ApproachEntity approachEntity);
    Optional<ApproachEntity> find(Object id);
    List<ApproachEntity> findAll();
}
