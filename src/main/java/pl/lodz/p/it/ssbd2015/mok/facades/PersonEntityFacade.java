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
public class PersonEntityFacade implements PersonEntityFacadeLocal
{
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
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findByLogin", PersonEntity.class);
        personQuery.setParameter("login", login);
//        getEntityManager().find(getEntityClass())
        try
        {
            return Optional.ofNullable(personQuery.getSingleResult());
        }
        catch (NoResultException e)
        {
            throw e;
        }
//        if(personQuery.getSingleResult()==null)
//        {
//            throw new NoResultException();
//        }

    }
    @Override
    public List<PersonEntity> findByPhrase(String phrase) {
        TypedQuery<PersonEntity> personQuery = entityManager.createNamedQuery("findByPhrase", PersonEntity.class);
        personQuery.setParameter("phrase", "%" + phrase + "%" );
        return personQuery.getResultList();
    }
}
