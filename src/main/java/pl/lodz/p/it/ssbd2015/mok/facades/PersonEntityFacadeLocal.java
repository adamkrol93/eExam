package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Create;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonEntityFacadeLocal extends Merge<Long, PersonEntity>, Create<Long, PersonEntity>, Read<Long, PersonEntity>{
    Optional<PersonEntity> findByLogin(String login);
    List<PersonEntity> findByPhrase(String phrase);
}
