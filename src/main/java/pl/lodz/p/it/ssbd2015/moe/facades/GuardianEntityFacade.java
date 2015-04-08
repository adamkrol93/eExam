package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by adam on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacade")
public class GuardianEntityFacade implements GuardianEntityFacadeLocal {
    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
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
