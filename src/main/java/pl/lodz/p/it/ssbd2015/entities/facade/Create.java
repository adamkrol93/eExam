package pl.lodz.p.it.ssbd2015.entities.facade;

import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

/**
 * Interfejs bazowy z defaultową implementacją crete.
 * Rozszerzenie go pozwala na łatwe dodanie możliwości tworzeniea nowych obiektów w bazie.
 * @author Michał Sośnicki
 * @author Bartosz Ignaczewski
 */
public interface Create<K, E> extends FacadeBase<K, E> {

    /**
     * Metoda zapisuje nową Encję do bazy
     * @param entity Encja którą chcemy zapisać
     * @throws ApplicationBaseException Rzucany, kiedy nie uda sie utworzyc encji
     */
    default void create(E entity) throws ApplicationBaseException {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

}
