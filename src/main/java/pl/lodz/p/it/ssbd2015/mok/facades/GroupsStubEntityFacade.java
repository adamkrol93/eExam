package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Marcin on 2015-04-08.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mok.facades.GroupsStubEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class GroupsStubEntityFacade implements GroupsStubEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mok_PU")
    private EntityManager entityManager;

    @Override
    public Class<GroupsStubEntity> getEntityClass() {
        return GroupsStubEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @RolesAllowed("CHANGE_GROUP_MOK")
    public GroupsStubEntity edit(GroupsStubEntity entity) {
        return GroupsStubEntityFacadeLocal.super.edit(entity);
    }
}
