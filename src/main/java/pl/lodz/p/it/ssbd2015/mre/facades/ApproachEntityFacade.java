package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ApproachEntityFacade implements ApproachEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    public Class<ApproachEntity> getEntityClass() {
        return ApproachEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void create(ApproachEntity entity) throws ApplicationBaseException {
        ApproachEntityFacadeLocal.super.create(entity);
    }

    @Override
    public void edit(ApproachEntity entity) {
        ApproachEntityFacadeLocal.super.edit(entity);

    }

    @Override
    public Optional<ApproachEntity> findById(Long id) {
        return ApproachEntityFacadeLocal.super.findById(id);
    }

    @Override
    public List<ApproachEntity> findAll() {
        return ApproachEntityFacadeLocal.super.findAll();
    }
}
