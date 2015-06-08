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
     * Nauczyciel wyświetla szczegóły podejścia
     * @param id identyfikator encji, której szukamy
     * @return ApproachEntity jeżeli coś znajdzie
     * @throws ApplicationBaseException Rzucany, kiedy nie znajdzie encji o podanym id
     */
    ApproachEntity findById(long id) throws ApplicationBaseException;

    /**
     * Metoda znajduje wszystkie egzaminy zalogowanego nauczyciela
     * @return lista egzaminów zalogowanego nauczyciela
     * @throws ApplicationBaseException Rzucany, kiedy nie znajdzie odpowiednich egzaminów
     */
    List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException;

}
