package pl.lodz.p.it.ssbd2015.moe;

import entities.CuratorEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface CuratorEntityFacadeLocal {
    Optional<CuratorEntity> find(Object id);
    List<CuratorEntity> findAll();
}
