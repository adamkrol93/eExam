package pl.lodz.p.it.ssbd2015.entities.facade;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Interfejs bazowy z defaultową implementacją merge.
 * Rozszerzenie go pozwala na łatwe dodanie możliwości modyfikowania obiektów w bazie.
 * @author Michał Sośnicki
 */
public interface Merge<K, E> extends FacadeBase<K, E> {

    /**
     * Metoda wykonuje merge oraz flush na przekazanej encji.
     * @param entity Encja która ma zostać zedytowana
     * @throws ApplicationBaseException Rzucany, kiedy nie uda sie merge i flush
     */
    default void
    edit(E entity) throws ApplicationBaseException {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

}
