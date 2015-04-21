package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.AnswerEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AnswerEntityFacade implements AnswerEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    public Class<AnswerEntity> getEntityClass() {
        return AnswerEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
