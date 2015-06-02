package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji Exam
 * @author Andrzej Kurczewski
 */
@Local
public interface ExamEntityFacadeLocal extends Merge<Long,ExamEntity>, Read<Long,ExamEntity>{


    /**
     * Funkcja zliczająca zakończone podejścia
     * @param examEntity egzamin którego podejścia zliczamy
     * @return liczba zakończonych podejść
     */
    long countExamFinished(ExamEntity examEntity);

    /**
     * Funckaj obliczająca średnią z zakończonych podejść
     * @param examEntity egzamin którego średnią chcemy policzyć
     * @return średnia wartość ocen podejść w egzaminie
     */
    double countAverage(ExamEntity examEntity);
}
