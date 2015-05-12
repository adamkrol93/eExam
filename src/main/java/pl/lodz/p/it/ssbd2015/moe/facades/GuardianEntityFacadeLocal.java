package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Guardian
 * @author Andrzej Kurczewski
 */
@Local
public interface GuardianEntityFacadeLocal extends Read<Long,GuardianEntity>{
}
