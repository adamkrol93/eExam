package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu {@link ExamEntityFacadeLocal}, pozwala na zarządzanie Egzaminami.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * @author Piotr Jurewicz
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
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
    @RolesAllowed({"CREATE_EXAM_MZE", "CLONE_EXAM_MZE"})
    public void create(ExamEntity entity) throws ApplicationBaseException {
        try {
            ExamEntityFacadeLocal.super.create(entity);
        } catch (IllegalArgumentException ex) {
            throw new ExamIllegalArgumentException(entity + " is an illegal argument to ExamEntityFacade.create(e)", ex);
        } catch (EntityExistsException ex) {
            throw new ExamExistsException(entity + " has been already persisted.", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("exam_title_key")) {
                throw new ExamTitleNotUnique("Title " + entity.getTitle() + " is not unique.", ex);
            } else if (ex.getMessage().contains("exam_exam_creator_id_fkey")) {
                throw new ExamCreatorForeignKeyException("Creator id is incorrect in entity: " + entity, ex);
            } else if (ex.getMessage().contains("exam_exam_modifier_id_fkey")) {
                throw new ExamModifierForeignKeyException("Modifier id is incorrect in entity: " + entity, ex);
            } else if (ex.getMessage().contains("exam_groups")) {
                throw new ExamTeacherForeignKeyException("Failed to join Exam and Teacher objects in ManyToMany relationship", ex);
            } else if (ex.getMessage().contains("exam_question")) {
                throw new ExamQuestionForeignKeyException("Failed to join Exam and Question objects in ManyToMany relationship", ex);
            } else {
                throw new ExamManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"EDIT_EXAM_MZE", "ADD_TEACHER_TO_EXAM_MZE", "REMOVE_QUESTION_FROM_EXAM_MZE", "REMOVE_TEACHER_FROM_EXAM_MZE"})
    public void edit(ExamEntity entity) throws ApplicationBaseException {
        ExamEntityFacadeLocal.super.edit(entity);
    }

    @Override
    @RolesAllowed({"EDIT_EXAM_MZE", "SHOW_EXAM_MZE", "SHOW_EXAM_STATS_MZE"})
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("LIST_EXAMS_MZE")
    public List<ExamEntity> findAll() {
        return ExamEntityFacadeLocal.super.findAll();
    }
}
