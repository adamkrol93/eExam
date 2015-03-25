package pl.lodz.p.it.ssbd2015.moe;

import entities.AnswerEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface AnswerEntityFacadeLocal {
    void update(AnswerEntity answerEntity);
    Optional<AnswerEntity> find(Object id);
    List<AnswerEntity> findAll();
}
