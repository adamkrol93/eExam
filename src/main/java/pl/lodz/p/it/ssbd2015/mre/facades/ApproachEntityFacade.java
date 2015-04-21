package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ApproachEntityFacade implements ApproachEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
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
