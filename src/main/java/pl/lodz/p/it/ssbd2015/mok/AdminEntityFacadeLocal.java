package pl.lodz.p.it.ssbd2015.mok;

import entities.AdminEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface AdminEntityFacadeLocal {
    void insert(AdminEntity adminEntity);
    void update(AdminEntity adminEntity);
    Optional<AdminEntity> find(Object id);
    List<AdminEntity> findAll();
}
