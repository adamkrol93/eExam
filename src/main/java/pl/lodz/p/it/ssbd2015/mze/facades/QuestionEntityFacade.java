package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
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

    @Override
    public void create(QuestionEntity entity) {
        QuestionEntityFacadeLocal.super.create(entity);
    }

    @Override
    public QuestionEntity edit(QuestionEntity entity) {
        return QuestionEntityFacadeLocal.super.edit(entity);
    }

    @Override
    public Optional<QuestionEntity> findById(Long id) {
        return QuestionEntityFacadeLocal.super.findById(id);
    }

    @Override
    public List<QuestionEntity> findAll() {
        return QuestionEntityFacadeLocal.super.findAll();
    }
}
