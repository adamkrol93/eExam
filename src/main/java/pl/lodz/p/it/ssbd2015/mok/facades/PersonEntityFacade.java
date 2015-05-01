package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.*;

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
    public Class<PersonEntity> getEntityClass() {
        return PersonEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @PermitAll
    public Optional<PersonEntity> findByLogin(String login) {
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findPersonByLogin", PersonEntity.class);
        personQuery.setParameter("login", login);

        try {
            return Optional.of(personQuery.getSingleResult());
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
            } else {
                throw new PersonUnknownException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed({"CONFIRM_ACCOUNT_MOK", "ACTIVATE_ACCOUNT_MOK", "EDIT_SOMEBODY_ACCOUNT_MOK"})
    public void edit(PersonEntity entity) {
        PersonEntityFacadeLocal.super.edit(entity);
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
