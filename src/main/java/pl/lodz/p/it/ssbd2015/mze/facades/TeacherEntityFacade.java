package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class TeacherEntityFacade implements TeacherEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<TeacherEntity> getEntityClass() {
        return TeacherEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("ADD_TEACHER_TO_EXAM_MZE")
    public List<TeacherEntity> findAllNotInExam(ExamEntity examEntity) {
        TypedQuery<TeacherEntity> query = entityManager.createNamedQuery("findAllTeacherNotInExam", TeacherEntity.class);
        query.setParameter("exam", examEntity);
        return query.getResultList();
    }

    @Override
    @RolesAllowed({"CREATE_EXAM_MZE", "SHOW_TEACHER_LIST_MZE"})
    public Optional<TeacherEntity> findById(Long id) {
        return TeacherEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"CREATE_EXAM_MZE", "SHOW_TEACHER_LIST_MZE"})
    public List<TeacherEntity> findAll() {
        return TeacherEntityFacadeLocal.super.findAll();
    }
}
