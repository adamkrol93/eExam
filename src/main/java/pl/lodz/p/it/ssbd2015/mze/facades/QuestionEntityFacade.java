package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class QuestionEntityFacade implements QuestionEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    public Class<QuestionEntity> getEntityClass() {
        return QuestionEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
