package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
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
 * Implementacja {@link GuardianEntityFacadeLocal}, zapewnia operacje bazodanowe na encji Guardian.
 * @author Michał Sośnicki
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.GuardianEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class GuardianEntityFacade implements GuardianEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<GuardianEntity> getEntityClass() {
        return GuardianEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MRE")
    public Optional<GuardianEntity> findById(Long id) {
        return GuardianEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MRE")
    public List<GuardianEntity> findAll() {
        return GuardianEntityFacadeLocal.super.findAll();
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MRE")
    public Optional<GuardianEntity> findByLogin(String login) {
        TypedQuery<GuardianEntity> guardianQuery = entityManager.createNamedQuery("findGuardianByLogin", GuardianEntity.class);
        guardianQuery.setParameter("login", login);
        try {
            return Optional.of(guardianQuery.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
