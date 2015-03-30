package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.AdministratorEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface AdministratorEntityFacadeLocal {
    void insert(AdministratorEntity administratorEntity);
    void update(AdministratorEntity administratorEntity);
    Optional<AdministratorEntity> find(Object id);
    List<AdministratorEntity> findAll();
}
