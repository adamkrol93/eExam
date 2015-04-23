package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExaminerEntityFacade implements ExaminerEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    public Class<ExaminerEntity> getEntityClass() {
        return ExaminerEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Optional<ExaminerEntity> findByLogin(String login) {
        TypedQuery<ExaminerEntity> query = entityManager.createNamedQuery("findExaminerByLogin", ExaminerEntity.class);
        query.setParameter("login", login);
        try {
            ExaminerEntity result = query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ExaminerEntity> findById(Long id) {
        return ExaminerEntityFacadeLocal.super.findById(id);
    }

    @Override
    public List<ExaminerEntity> findAll() {
        return ExaminerEntityFacadeLocal.super.findAll();
    }
}
