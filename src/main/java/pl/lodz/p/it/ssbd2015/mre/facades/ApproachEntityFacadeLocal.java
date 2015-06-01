package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Create;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs do operacji bazodanowych dla encji {@link ApproachEntity}
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * @author Andrzej Kurczewski
 */
@Local
public interface ApproachEntityFacadeLocal extends Read<Long, ApproachEntity>, Create<Long, ApproachEntity>, Merge<Long, ApproachEntity> {

    /**
     * Metoda która pozwala na zablokowanie encji za pomocą OPTIMISTIC_FORCE_INCREMENT
     * @param examEntity egzamin który blokujemy
     */
    void lockWrite(ExamEntity examEntity);

    /**
     * Metoda która pozwala na zablokowanie encji za pomocą OPTIMISTIC_FORCE_INCREMENT
     * @param questionEntity pytanie które blokujemy
     */
    void lockWrite(QuestionEntity questionEntity);
}
