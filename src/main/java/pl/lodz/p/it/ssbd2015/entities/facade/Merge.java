package pl.lodz.p.it.ssbd2015.entities.facade;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Merge<K, E> extends FacadeBase<K, E> {

    default void edit(E entity) throws ApplicationBaseException {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

}
