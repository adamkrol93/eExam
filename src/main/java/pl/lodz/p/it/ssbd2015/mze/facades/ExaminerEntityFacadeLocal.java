package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExaminerEntityFacadeLocal extends Read<Long, ExaminerEntity> {

}
