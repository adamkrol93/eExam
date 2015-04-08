package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface ExaminerEntityFacadeLocal extends Read<Long, ExaminerEntity> {
    Optional<ExaminerEntity> findByLogin(String login);
}
