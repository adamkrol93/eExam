package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
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
 * Implementacja interfejsu {@link StudentEntityFacadeLocal}. Pozwala na operacje bazodanowe na encji {@link StudentEntity}
 * @author Michał Sośnicki
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class StudentEntityFacade implements StudentEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<StudentEntity> getEntityClass() {
        return StudentEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed({"START_SOLVING_EXAM_MRE", "LIST_APPROACHES_MRE"})
    public Optional<StudentEntity> findById(Long id) {
        return StudentEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"START_SOLVING_EXAM_MRE", "LIST_APPROACHES_MRE"})
    public List<StudentEntity> findAll() {
        return StudentEntityFacadeLocal.super.findAll();
    }

    @Override
    @RolesAllowed({"START_SOLVING_EXAM_MRE", "LIST_APPROACHES_MRE"})
    public Optional<StudentEntity> findByLogin(String login) {
        TypedQuery<StudentEntity> studentQuery = entityManager.createNamedQuery("findStudentByLogin", StudentEntity.class);
        studentQuery.setParameter("login", login);
        try {
            return Optional.of(studentQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
