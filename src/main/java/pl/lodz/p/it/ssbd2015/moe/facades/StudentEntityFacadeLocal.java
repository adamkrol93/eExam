package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Student
 * @author Andrzej Kurczewski
 */
@Local
public interface StudentEntityFacadeLocal extends Merge<Long,StudentEntity>, Read<Long,StudentEntity>{
}
