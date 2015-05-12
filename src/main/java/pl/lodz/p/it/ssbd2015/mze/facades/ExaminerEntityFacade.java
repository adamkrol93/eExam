package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
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
 * Implementacja interfejsu {@link ExaminerEntityFacadeLocal}, pozwala na wyszukiwania Egzaminatorów.
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExaminerEntityFacade implements ExaminerEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<ExaminerEntity> getEntityClass() {
        return ExaminerEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed({"CREATE_EXAM_MZE", "CREATE_QUESTION_MZE", "EDIT_QUESTION_MZE", "EDIT_EXAM_MZE",
            "ADD_TEACHER_TO_EXAM_MZE", "REMOVE_QUESTION_FROM_EXAM_MZE", "REMOVE_TEACHER_FROM_EXAM_MZE", "CLONE_EXAM_MZE"})
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
    @RolesAllowed({"CREATE_EXAM_MZE", "CREATE_QUESTION_MZE", "EDIT_QUESTION_MZE", "EDIT_EXAM_MZE",
            "ADD_TEACHER_TO_EXAM_MZE", "REMOVE_QUESTION_FROM_EXAM_MZE", "REMOVE_TEACHER_FROM_EXAM_MZE", "CLONE_EXAM_MZE"})
    public Optional<ExaminerEntity> findById(Long id) {
        return ExaminerEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"CREATE_EXAM_MZE", "CREATE_QUESTION_MZE", "EDIT_QUESTION_MZE", "EDIT_EXAM_MZE",
            "ADD_TEACHER_TO_EXAM_MZE", "REMOVE_QUESTION_FROM_EXAM_MZE", "REMOVE_TEACHER_FROM_EXAM_MZE", "CLONE_EXAM_MZE"})
    public List<ExaminerEntity> findAll() {
        return ExaminerEntityFacadeLocal.super.findAll();
    }
}
