package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
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
    public List<ExamEntity> findByDate(Calendar timestamp) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByDate", ExamEntity.class);
        examQuery.setParameter("date", timestamp, TemporalType.TIMESTAMP);
        return examQuery.getResultList();
    }

    @Override
    public void edit(ExamEntity entity) {
        ExamEntityFacadeLocal.super.edit(entity);
    }

    @Override
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntityFacadeLocal.super.findById(id);
    }

    @Override
    public List<ExamEntity> findAll() {
        return ExamEntityFacadeLocal.super.findAll();
    }
}
