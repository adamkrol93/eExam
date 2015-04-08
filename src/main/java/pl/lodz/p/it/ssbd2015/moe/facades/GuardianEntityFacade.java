package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by adam on 08.04.15.
 */
public class GuardianEntityFacade implements GuardianEntityFacadeLocal {
    @PersistenceContext(name = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    public Class<GuardianEntity> getEntityClass() {
        return GuardianEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
