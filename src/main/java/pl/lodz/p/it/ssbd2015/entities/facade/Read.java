package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public interface Read<K, E> extends FacadeBase<K, E> {

    default Optional<E> findById(K id) {
        if (id == null) {
            return Optional.empty();
        }
        E entity = getEntityManager().find(getEntityClass(), id);
        return Optional.ofNullable(entity);
    }

    default List<E> findAll() {
        String findAllStr = String.format("SELECT e FROM %s e", getEntityClass().getCanonicalName());
        TypedQuery<E> query = getEntityManager().createQuery(findAllStr, getEntityClass());
        return query.getResultList();
    }

}
