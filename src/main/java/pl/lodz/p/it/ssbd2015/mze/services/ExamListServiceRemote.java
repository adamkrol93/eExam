package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs pozwalający na wyświetlenie wszystkich egzaminów oraz skolnowanie egzaminu.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ExamListServiceRemote {


    /**
     * Metoda pozwala na wyszukanie wszystkich egzaminów w systemie.
     * @return Lista ({@link List}) z egzaminami w systemie
     */
    List<ExamEntity> findAll();

    void cloneExam(long examId) throws ApplicationBaseException;

}
