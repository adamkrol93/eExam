package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.EntityManager;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Delete<K, E> extends FacadeBase<K, E> {

    default void remove(K id) {
        EntityManager entityManager = getEntityManager();
        E entity = entityManager.find(getEntityClass(), id);
        entityManager.remove(entity);
        entityManager.flush();
    }

}
