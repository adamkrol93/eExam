package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Approach.
 * @author Andrzej Kurczewski
 */
@Local
public interface ApproachEntityFacadeLocal extends Merge<Long,ApproachEntity>, Read<Long,ApproachEntity>{
}
