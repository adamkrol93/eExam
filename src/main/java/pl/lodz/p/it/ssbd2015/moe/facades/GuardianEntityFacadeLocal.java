package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface GuardianEntityFacadeLocal {
    Optional<GuardianEntity> find(Object id);
    List<GuardianEntity> findAll();
}
