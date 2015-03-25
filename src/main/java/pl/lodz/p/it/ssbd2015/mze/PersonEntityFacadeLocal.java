package pl.lodz.p.it.ssbd2015.mze;

import entities.PersonEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonEntityFacadeLocal {
    Optional<PersonEntity> find(Object id);
    List<PersonEntity> findAll();
}
