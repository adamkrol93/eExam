package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Exam
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal extends Merge<Long,ExamEntity>, Read<Long,ExamEntity>{

}
