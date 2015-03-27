package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.CuratorEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface CuratorEntityFacadeLocal {
    void insert(CuratorEntity curatorEntity);
    void update(CuratorEntity curatorEntity);
    Optional<CuratorEntity> find(Object id);
    List<CuratorEntity> findAll();
}
