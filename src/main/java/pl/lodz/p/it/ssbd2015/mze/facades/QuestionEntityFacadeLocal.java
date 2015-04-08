package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Create;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface QuestionEntityFacadeLocal extends Read<Long, QuestionEntity>, Create<Long, QuestionEntity>, Merge<Long, QuestionEntity> {

}
