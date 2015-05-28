package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

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
 * Implementacja interfejsu {@link ApproachEntityFacadeLocal}. Pozwala na operacje bazodanowe na encji {@link ApproachEntity}
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
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
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public void create(ApproachEntity entity) throws ApplicationBaseException {
        ApproachEntityFacadeLocal.super.create(entity);
    }

    @Override
    @RolesAllowed({"ANSWER_QUESTION_MRE", "SHOW_APPROACH_MOE"})
    public void edit(ApproachEntity entity) throws ApplicationBaseException {
        ApproachEntityFacadeLocal.super.edit(entity);
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
}
