package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
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
 * Created by tobiasz_kowalski on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ExamEntitySerializableFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntitySerializableFacade implements ExamEntitySerializableFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moeser_PU")
    private EntityManager entityManager;

    @Override
    public Class<ExamEntity> getEntityClass() {
        return ExamEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public ExamEntity edit(ExamEntity entity) {
        return ExamEntitySerializableFacadeLocal.super.edit(entity);
    }

    @Override
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntitySerializableFacadeLocal.super.findById(id);
    }

    @Override
    public List<ExamEntity> findAll() {
        return ExamEntitySerializableFacadeLocal.super.findAll();
    }
}
