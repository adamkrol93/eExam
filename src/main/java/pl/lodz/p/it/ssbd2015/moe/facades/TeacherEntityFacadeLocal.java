package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 * @author Adam Król
 */
@Local
public interface TeacherEntityFacadeLocal extends Read<Long,TeacherEntity>{
    Optional<TeacherEntity> findByLogin(String login);
}
