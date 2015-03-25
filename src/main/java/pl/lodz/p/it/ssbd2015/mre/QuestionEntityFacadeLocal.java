package pl.lodz.p.it.ssbd2015.mre;

import entities.QuestionEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface QuestionEntityFacadeLocal {
    Optional<QuestionEntity> find(Object id);
    List<QuestionEntity> findAll();
}
