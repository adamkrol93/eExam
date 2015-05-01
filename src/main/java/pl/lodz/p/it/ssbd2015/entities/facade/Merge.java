package pl.lodz.p.it.ssbd2015.entities.facade;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Merge<K, E> extends FacadeBase<K, E> {

    default void edit(E entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

}
