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
     * Wyświetla egzaminatorowi wszystkie egzaminy, które znajdują się w systemie egzaminacyjnym.
     * @return Lista ({@link List}) z egzaminami w systemie
     */
    List<ExamEntity> findAll();

    /**
     * Egzaminator kopiuje egzamin
     * @param examId Identyfikator egzaminu do skopiowania.
     * @throws ApplicationBaseException Rzucany, gdy egzamin nie został odnaleziony i jeżeli jakimś
     * cudem baza nie chce przyjąć kopii.
     */
    void cloneExam(long examId) throws ApplicationBaseException;

}
