package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.EntityManager;

/**
 * Interfejs bazowy z defaultową implementacją remove.
 * Rozszerzenie go pozwala na łatwe dodanie możliwości usunięcia obiektów z bazy.
 * @author Michał Sośnicki
 */
public interface Delete<K, E> extends FacadeBase<K, E> {

    /**
     * Metoda usuwająca z bazy danych encję o podanym id
     * @param id identyfikator Encji, którą chcemy usunąć
     */
    default void remove(K id) {
        EntityManager entityManager = getEntityManager();
        E entity = entityManager.find(getEntityClass(), id);
        entityManager.remove(entity);
        entityManager.flush();
    }

}
