package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal extends Read<Long, ExamEntity>, Merge<Long, ExamEntity> {

    List<ExamEntity> findByDate(Calendar timestamp);

    Optional<ExamEntity> findByTitle(String title);
}
