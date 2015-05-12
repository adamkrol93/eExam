package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Klasa obsługująca obsługę bazodanową encji Guardian.
 * @author Adam Król
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class GuardianEntityFacade implements GuardianEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<GuardianEntity> getEntityClass() {
        return GuardianEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public Optional<GuardianEntity> findById(Long id) {
        return GuardianEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public List<GuardianEntity> findAll() {
        return GuardianEntityFacadeLocal.super.findAll();
    }
}
