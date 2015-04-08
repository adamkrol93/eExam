package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacade")
public class ApproachEntityFacade implements ApproachEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;


    @Override
    public Class<ApproachEntity> getEntityClass() {

        return ApproachEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
}
