package pl.lodz.p.it.ssbd2015.entities.facade;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Create<K, E> extends FacadeBase<K, E> {

    default void create(E entity) throws ApplicationBaseException {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

}
