package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface TeacherEntityFacadeLocal extends Read<Long, TeacherEntity> {
    List<TeacherEntity> findAllNotInExam(ExamEntity examEntity);
}
