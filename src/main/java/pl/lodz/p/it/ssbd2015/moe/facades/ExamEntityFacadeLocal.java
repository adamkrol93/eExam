package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamStatsEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Exam
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal extends Merge<Long,ExamEntity>, Read<Long,ExamEntity>{

    /**
     * Funkcja zliczająca zakończone podejścia
     * @param examId id egzaminu którego podejścia zliczamy
     * @return liczba zakończonych podejść
     */
    long countExamFinished(long examId);

    /**
     * Funckaj obliczająca średnią z zakończonych podejść
     * @param examId id egzaminu którego średnią chcemy policzyć
     * @return średnia wartość ocen podejść w egzaminie
     */
    long sumApproachesGrades(long examId);

    /**
     * Zakłada blokadę pesymistyczną na encji statystyk egzaminu.
     * @param exam Encja do zablokowania.
     */
    void lockPessimisticWrite(ExamStatsEntity exam);

    /**
     * Wyszukuje encję statystyk egzaminu o podanym id
     * @param id Id encji do wyszukania.
     * @return Być może encja o podanym id.
     */
    Optional<ExamStatsEntity> findStatsById(long id);

    /**
     * Zapisuje w bazie zmiany dokonane na przekazanej encji.
     * @param examStats Encja statystyk egzaminu do wyedytowania.
     */
    void editStats(ExamStatsEntity examStats);

}
