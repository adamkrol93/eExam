package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface QuestionEntityFacadeLocal {
    void insert(QuestionEntity questionEntity);
    void update(QuestionEntity questionEntity);
    void delete(QuestionEntity questionEntity);
    Optional<QuestionEntity> find(Object id);
    List<QuestionEntity> findAll();
}
