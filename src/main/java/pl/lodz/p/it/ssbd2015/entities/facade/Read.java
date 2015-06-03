package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs bazowy z defaultową implementacją findById oraz findAll.
 * Rozszerzenie go pozwala na łatwe dodanie możliwości wyszukiwania obiektów w bazie
 * @author Michał Sośnicki
 */
public interface Read<K, E> extends FacadeBase<K, E> {

    /**
     * Metoda wyszukuje w bazie danych encję o podanym Id
     * @param id identyfikator encji której szukamy
     * @return Znaleziona encja opakowana w Optional (zapobiega nullom)
     */
    default Optional<E> findById(K id) {
        if (id == null) {
            return Optional.empty();
        }
        E entity = getEntityManager().find(getEntityClass(), id);
        return Optional.ofNullable(entity);
    }

    /**
     * Wyciąga z bazy wszystkie encje danego typu
     * @return Lista znalezionych encji
     */
    default List<E> findAll() {
        String findAllStr = String.format("SELECT e FROM %s e", getEntityClass().getCanonicalName());
        TypedQuery<E> query = getEntityManager().createQuery(findAllStr, getEntityClass());
        return query.getResultList();
    }

}
