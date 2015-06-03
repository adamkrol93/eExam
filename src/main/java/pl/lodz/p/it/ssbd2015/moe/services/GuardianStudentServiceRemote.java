package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 *Interfejs do obsługi łączenia studenta i opiekuna.
 *@author Bartosz Ignaczewski
 */
@Remote
public interface GuardianStudentServiceRemote {

    /**
     * Wyszukuje w bazie danych wszystkich opiekunów
     * @return lista opiekunów w aplikacji
     */
    List<GuardianEntity> findAllGuardians();


    /**
     * Wyszukuje w bazie danych wszystkich studentów
     * @return lista studentów w aplikacji
     */
    List<StudentEntity> findAllStudents();


    /**
     * Dokonuje przypisania opiekuna do studenta
     * @param guardianId identyfikator opiekuna do przypisania
     * @param studentId identyfikator studenta do przypisania
     * @throws ApplicationBaseException Rzucany, kiedy nie dokona przypisania
     */
    void connect(long guardianId, long studentId) throws ApplicationBaseException;

}
