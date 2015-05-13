package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.*;
import pl.lodz.p.it.ssbd2015.exceptions.mok.*;

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

/**
 * Klasa realizuje połaczenie z bazą danych dla encji GroupsStub.
 * @author Marcin Kabza
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mok.facades.GroupsStubEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class GroupsStubEntityFacade implements GroupsStubEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mok_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<GroupsStubEntity> getEntityClass() {
        return GroupsStubEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @RolesAllowed("CHANGE_GROUP_MOK")
    public void edit(GroupsStubEntity entity) throws ApplicationBaseException {
        try {
            GroupsStubEntityFacadeLocal.super.edit(entity);
        } catch (IllegalArgumentException ex) {
            throw new GroupsIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)", ex);
        } catch (OptimisticLockException ex) {
            throw new GroupsOptimisticLockException(entity + " is being edit by someone else", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("groups_groups_guardian_fkey")) {
                throw new GroupsGuardianForeignKeyException("Guardian id is incorrect for entity:" + entity, ex);
            } else if (ex.getMessage().contains("groups_person_id_fkey")) {
                throw new GroupsPersonForeignKeyException("PersonEntity id is incorrect for entity" + entity,ex);
            } else {
                throw new GroupsManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }
}
