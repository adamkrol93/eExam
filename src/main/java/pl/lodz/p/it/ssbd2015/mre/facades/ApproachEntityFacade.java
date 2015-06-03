package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu {@link ApproachEntityFacadeLocal}. Pozwala na operacje bazodanowe na encji {@link ApproachEntity}
 * @author Michał Sośnicki
 * @author Piotr Jurewicz
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ApproachEntityFacade implements ApproachEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<ApproachEntity> getEntityClass() {
        return ApproachEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public void create(ApproachEntity entity) throws ApplicationBaseException {
        try{
        ApproachEntityFacadeLocal.super.create(entity);
        } catch (IllegalArgumentException ex) {
            throw new ApproachIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)", ex);
        } catch (EntityExistsException ex) {
            throw new ApproachExistsException(entity + " has been already persisted.", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("approach_approach_entrant_id_fkey")) {
                throw new ApproachEntrantForeignKeyException("Entrant id is incorrect for entity:" + entity, ex);
            } else if (ex.getMessage().contains("approach_approach_exam_id_fkey")) {
                throw new ApproachExamForeignKeyException("Exam id is incorrect for entity" + entity, ex);
            } else {
                throw new ApproachManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"ANSWER_QUESTION_MRE", "SHOW_APPROACH_MOE"})
    public void edit(ApproachEntity entity) throws ApplicationBaseException {
        try{
        ApproachEntityFacadeLocal.super.edit(entity);
        } catch (IllegalArgumentException ex) {
            throw new ApproachIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)", ex);
        } catch (OptimisticLockException ex) {
            throw new ApproachOptimisticLockException(entity + " is being edit by someone else", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("approach_approach_entrant_id_fkey")) {
                throw new ApproachEntrantForeignKeyException("Entrant id is incorrect for entity:" + entity, ex);
            } else if (ex.getMessage().contains("approach_approach_exam_id_fkey")) {
                throw new ApproachExamForeignKeyException("Exam id is incorrect for entity" + entity, ex);
            } else {
                throw new ApproachManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"ANSWER_QUESTION_MRE", "SHOW_APPROACHES_MRE"})
    public Optional<ApproachEntity> findById(Long id) {
        return ApproachEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"ANSWER_QUESTION_MRE", "SHOW_APPROACHES_MRE"})
    public List<ApproachEntity> findAll() {
        return ApproachEntityFacadeLocal.super.findAll();
    }

    @Override
    @RolesAllowed({"START_SOLVING_EXAM_MRE"})
    public void lockWrite(ExamEntity examEntity) {
        getEntityManager().lock(examEntity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }

    @Override
    @RolesAllowed({"START_SOLVING_EXAM_MRE"})
    public void lockWrite(QuestionEntity questionEntity) {
        getEntityManager().lock(questionEntity,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}
