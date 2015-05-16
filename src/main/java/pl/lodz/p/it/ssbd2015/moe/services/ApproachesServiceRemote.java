package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs do obsługi odczytów z bazy encji Approach.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ApproachesServiceRemote {

    /**
     * Wyszukuje w bazie encji o podanym id
     * @param id identyfikator, któ©ego szukamy
     * @return ApproachEntity jeżeli coś znajdzie
     * @throws ApplicationBaseException
     */
    ApproachEntity findById(long id) throws ApplicationBaseException;

    /**
     * Metoda znajduje wszystkie egzaminy zalogowanego nauczyciela
     * @return lista egzaminów zalogowanego nauczyciela
     * @throws ApplicationBaseException
     */
    List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException;

}
