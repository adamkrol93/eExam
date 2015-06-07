package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
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
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu {@link ExamEntityFacadeLocal}. Pozwala na operacje bazodanowe na encji {@link ExamEntity}
 * @author Michał Sośnicki
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
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
    @RolesAllowed("END_APPROACH_MRE")
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
    @RolesAllowed("END_APPROACH_MRE")
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public List<ExamEntity> findAll() {
        return ExamEntityFacadeLocal.super.findAll();
    }


    @Override
    @RolesAllowed("LIST_AVAILABLE_EXAMS")
    public List<ExamEntity> findByDate(Calendar timestamp) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByDate", ExamEntity.class);
        examQuery.setParameter("date", timestamp, TemporalType.TIMESTAMP);
        return examQuery.getResultList();
    }

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public Optional<ExamEntity> findByTitle(String title) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByTitle", ExamEntity.class);
        examQuery.setParameter("title", title);

        try {
            ExamEntity examEntity = examQuery.getSingleResult();
            return Optional.of(examEntity);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
