package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfejs do wyszukiwania Nauczycieli w bazie danych.
 * @author Andrzej Kurczewski
 */
@Local
public interface TeacherEntityFacadeLocal extends Read<Long, TeacherEntity> {
    /**
     * Pobiera wszystkich nauczycieli którzy nie są przypisani do danego egzaminu. Wykorzystywana do dodania nauczyciela do egzaminu.
     * @param examEntity egzamin na podstawie którego szukani sa nauczyciele nie przypisani do niego
     * @return zwraca listę nauczycieli nie przypisanych do danego egzaminu
     */
    List<TeacherEntity> findAllNotInExam(ExamEntity examEntity);
}
