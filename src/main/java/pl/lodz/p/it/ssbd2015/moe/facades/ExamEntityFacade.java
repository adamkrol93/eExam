package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
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
 * Klasa obsługująca obsługę bazodanową encji Exam.
 * @author Adam Król
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<ExamEntity> getEntityClass() {
        return ExamEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public List<ExamEntity> findAll() {
        return ExamEntityFacadeLocal.super.findAll();
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public void edit(ExamEntity entity) throws ApplicationBaseException {
        ExamEntityFacadeLocal.super.edit(entity);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public long countExamFinished(ExamEntity examEntity) {
        TypedQuery<Long> examQuery = entityManager.createNamedQuery("ExamEntity.countFinished", Long.class);
        examQuery.setParameter("exam",examEntity);
        examQuery.setParameter("actualdate", Calendar.getInstance().getTime(), TemporalType.TIMESTAMP);
        return examQuery.getSingleResult() == null ? 0 : examQuery.getSingleResult();
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public double countAverage(ExamEntity examEntity) {
        TypedQuery<Long> examQuery = entityManager.createNamedQuery("ExamEntity.examAverage", Long.class);
        examQuery.setParameter("exam",examEntity);
        examQuery.setParameter("actualdate", Calendar.getInstance().getTime(), TemporalType.TIMESTAMP);
        return examQuery.getSingleResult() == null ? 0.0 : (double)examQuery.getSingleResult();
    }
}
