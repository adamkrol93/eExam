package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.EntityManager;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface FacadeBase<K, E> {

    Class<E> getEntityClass();

    EntityManager getEntityManager();

}
