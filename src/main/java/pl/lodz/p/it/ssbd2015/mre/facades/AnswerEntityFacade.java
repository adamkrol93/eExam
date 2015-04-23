package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
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
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.AnswerEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
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

    @Override
    public AnswerEntity edit(AnswerEntity entity) {
        return AnswerEntityFacadeLocal.super.edit(entity);
    }

    @Override
    public Optional<AnswerEntity> findById(Long id) {
        return AnswerEntityFacadeLocal.super.findById(id);
    }

    @Override
    public List<AnswerEntity> findAll() {
        return AnswerEntityFacadeLocal.super.findAll();
    }

}
