package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamStatsEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.*;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamTitleNotUniqueException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Klasa obsługująca obsługę bazodanową encji Exam.
 *
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
        try {
            ExamEntityFacadeLocal.super.edit(entity);
        } catch (IllegalArgumentException ex) {
            throw new ExamIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)", ex);
        } catch (OptimisticLockException ex) {
            throw new ExamOptimisticLockException(entity + " is being edit by someone else", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("exam_title_key")) {
                throw new ExamTitleNotUniqueException("Exam Title is not unique for entity:" + entity, ex);
            } else if (ex.getMessage().contains("exam_exam_modifier_id_fkey")) {
                throw new ExamModifierForeignKeyException("Modifier id is incorrect for entity" + entity, ex);
            } else if (ex.getMessage().contains("exam_exam_creator_id_fkey")) {
                throw new ExamCreatorForeignKeyException("Creator id is incorrect for entity" + entity, ex);
            } else {
                throw new ExamManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public long countExamFinished(long examId) {
        TypedQuery<Long> examQuery = entityManager.createNamedQuery("ExamEntity.countFinished", Long.class);
        examQuery.setParameter("examId", examId);
        examQuery.setParameter("currentdate", Calendar.getInstance().getTime(), TemporalType.TIMESTAMP);
        return examQuery.getSingleResult() == null ? 0 : examQuery.getSingleResult();
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public long sumApproachesGrades(long examId) {
        TypedQuery<Long> examQuery = entityManager.createNamedQuery("ExamEntity.sumApproachesGrades", Long.class);
        examQuery.setParameter("examId", examId);
        examQuery.setParameter("currentdate", Calendar.getInstance().getTime(), TemporalType.TIMESTAMP);
        return examQuery.getSingleResult() == null ? 0 : examQuery.getSingleResult();
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public void lockPessimisticWrite(ExamStatsEntity exam) {
        getEntityManager().refresh(exam, LockModeType.PESSIMISTIC_WRITE);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public Optional<ExamStatsEntity> findStatsById(long id) {
        ExamStatsEntity entity = getEntityManager().find(ExamStatsEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public void editStats(ExamStatsEntity examStats) {
        getEntityManager().merge(examStats);
        getEntityManager().flush();
    }
}
