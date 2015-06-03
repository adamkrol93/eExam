package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

/**
 * Klasa obsługująca obsługę bazodanową encji Approach.
 *
 * @author Tobiasz Kowalski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ApproachEntityFacade implements ApproachEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
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
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public void edit(ApproachEntity entity) throws ApplicationBaseException {
        try {
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
    @RolesAllowed("SHOW_APPROACH_MOE")
    public Optional<ApproachEntity> findById(Long id) {
        return ApproachEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE", "SHOW_APPROACH_MOE"})
    public List<ApproachEntity> findAll() {
        return ApproachEntityFacadeLocal.super.findAll();
    }
}
