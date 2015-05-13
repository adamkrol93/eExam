package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.*;
import pl.lodz.p.it.ssbd2015.exceptions.mok.*;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Klasa zapewnia mozliwośc operowania na encjach typu {@link PersonEntity}
 * @author Marcin Kabza
 * @author Adam Król
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class PersonEntityFacade implements PersonEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mok_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<PersonEntity> getEntityClass() {
        return PersonEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @PermitAll
    public Optional<PersonEntity> findByLogin(String login) {
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findPersonByLogin", PersonEntity.class);
        personQuery.setParameter("login", login);

        try {
            PersonEntity personEntity = personQuery.getSingleResult();
            return Optional.of(personEntity);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @RolesAllowed("SEARCH_FOR_ACCOUNT_MOK")
    public List<PersonEntity> findByPhrase(String phrase) {
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findPersonByPhrase", PersonEntity.class);
        personQuery.setParameter("phrase", "%" + phrase + "%" );
        return personQuery.getResultList();
    }

    @Override
    @PermitAll
    public void create(PersonEntity entity) throws ApplicationBaseException {
        try {
            PersonEntityFacadeLocal.super.create(entity);
        } catch (IllegalArgumentException ex) {
            throw new PersonIllegalArgumentException(entity + " is an illegal argument to Create.create(e)", ex);
        } catch (EntityExistsException ex) {
            throw new PersonExistsException(entity + " has been already persisted.", ex);
        } catch (PersistenceException ex) {
            if (ex.getMessage().contains("person_login_key")) {
                throw new LoginNotUniqueException("Login " + entity.getLogin() + " is not unique.", ex);
            } else if (ex.getMessage().contains("groups_groups_guardian_fkey")) {
                throw new GroupsGuardianForeignKeyException("Guardian id is incorrect for entity:" + entity, ex);
            } else if (ex.getMessage().contains("groups_person_id_fkey")) {
                throw new GroupsPersonForeignKeyException("PersonEntity id is incorrect for entity" + entity, ex);
            }else {
                throw new PersonManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"CONFIRM_ACCOUNT_MOK", "ACTIVATE_ACCOUNT_MOK", "EDIT_SOMEBODY_ACCOUNT_MOK", "ALL_LOGGED"})
    public void edit(PersonEntity entity) throws ApplicationBaseException {
        try {
            PersonEntityFacadeLocal.super.edit(entity);
        }catch (IllegalArgumentException ex)
        {
            throw new PersonIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)", ex);
        }catch (OptimisticLockException ex)
        {
            throw new PersonOptimisticLockException(entity + " is being edit by someone else",ex);
        }catch (PersistenceException ex)
        {
            if (ex.getMessage().contains("person_login_key")) {
                throw new LoginNotUniqueException("Login " + entity.getLogin() + " is not unique.", ex);
            } else if (ex.getMessage().contains("groups_groups_guardian_fkey")) {
                throw new GroupsGuardianForeignKeyException("Guardian id is incorrect for entity:" + entity, ex);
            } else if (ex.getMessage().contains("groups_person_id_fkey")) {
                throw new GroupsPersonForeignKeyException("PersonEntity id is incorrect for entity" + entity, ex);
            }else {
                throw new PersonManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed("LIST_ACCOUNTS_MOK")
    public Optional<PersonEntity> findById(Long id) {
        return PersonEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("LIST_ACCOUNTS_MOK")
    public List<PersonEntity> findAll() {
        return PersonEntityFacadeLocal.super.findAll();
    }
}
