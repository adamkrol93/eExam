package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
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
 * Created by adam on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class TeacherEntityFacade implements TeacherEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<TeacherEntity> getEntityClass() {
        return TeacherEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "LIST_APPROACHES_MOE"})
    public Optional<TeacherEntity> findById(Long id) {
        return TeacherEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "LIST_APPROACHES_MOE"})
    public List<TeacherEntity> findAll() {
        return TeacherEntityFacadeLocal.super.findAll();
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "LIST_APPROACHES_MOE"})
    public Optional<TeacherEntity> findByLogin(String login) {
        TypedQuery<TeacherEntity> typedQuery = entityManager.createNamedQuery("findTeacherByLogin", TeacherEntity.class);
        typedQuery.setParameter("login",login);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
