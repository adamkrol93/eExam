package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Local
public interface GuardianEntityFacadeLocal extends Read<Long, GuardianEntity> {
    Optional<GuardianEntity> findByLogin(String login);
}
