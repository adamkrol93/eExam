package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;

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
 * Created by Marcin on 2015-04-08.
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
    @RolesAllowed({"SHOW_SOMEBODY_ACCOUNT_MOK"/*, "SHOW_RAPORT_MOK", "EDIT_SOMEBODY_ACCOUNT_MOK"*/})
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
    public void create(PersonEntity entity) {
        PersonEntityFacadeLocal.super.create(entity);
    }

    @Override
    @RolesAllowed({"ACTIVATE_ACCOUNT_MOK", "LOCK_ACCOUNT_MOK", "ALL_LOGGED"})
    public PersonEntity edit(PersonEntity entity) {
        return PersonEntityFacadeLocal.super.edit(entity);
    }

    @Override
    public Optional<PersonEntity> findById(Long id) {
        return PersonEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("LIST_ACCOUNTS_MOK")
    public List<PersonEntity> findAll() {
        return PersonEntityFacadeLocal.super.findAll();
    }
}
