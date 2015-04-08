package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by adam on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacade")
public class StudentEntityFacade implements StudentEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    public Class<StudentEntity> getEntityClass() {
        return StudentEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
