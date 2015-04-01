package pl.lodz.p.it.ssbd2015.entities.facade;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Create<K, E> extends FacadeBase<K, E> {

    default void create(E entity) {
        getEntityManager().persist(entity);
    }

}
