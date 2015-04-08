package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface AnswerEntityFacadeLocal extends Read<Long,AnswerEntity>, Merge<Long,AnswerEntity>{
}
