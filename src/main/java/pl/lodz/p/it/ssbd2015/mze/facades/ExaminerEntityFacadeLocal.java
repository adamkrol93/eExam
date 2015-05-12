package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * Interfejs do wyszukiwania Egzaminatorów w bazie danych.
 * @author Andrzej Kurczewski
 */
@Local
public interface ExaminerEntityFacadeLocal extends Read<Long, ExaminerEntity> {
    /**
     * Wyszukuje Egzaminatora w bazie danych o podanym loginie
     * @param login login egzaminatora, którego szukamy
     * @return Optional z Egzaminatorem lub nullem
     */
    Optional<ExaminerEntity> findByLogin(String login);
}
