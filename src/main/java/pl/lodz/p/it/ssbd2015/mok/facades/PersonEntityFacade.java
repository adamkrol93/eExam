package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import javax.ejb.Stateless;
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
    public List<PersonEntity> findByPhrase(String phrase) {
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findPersonByPhrase", PersonEntity.class);
        personQuery.setParameter("phrase", "%" + phrase + "%" );
        return personQuery.getResultList();
    }
}
