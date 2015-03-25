package pl.lodz.p.it.ssbd2015.mok;

import entities.PersonEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonEntityFacadeLocal {
    void insert(PersonEntity personEntity);
    void update(PersonEntity personEntity);
    Optional<PersonEntity> find(Object id);
    List<PersonEntity> findAll();
}
