package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs pozwalający na zarządzani zakończonymi podejściami, a raczej na ich wyświetlanie.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ApproachesServiceRemote {

    /**
     * Zwraca listę podejść dla obecnie zalogowanego uczenia.
     * @return Lista podejść obecnie zalogowanego ucznia.
     * @throws ApplicationBaseException Jeżeli np. osoba wywołująca nie jest studentem.
     */
    List<ApproachEntity> listAllForStudent() throws ApplicationBaseException;

    /**
     * Zwraca listę będącą złączeniem list podejść wszystkich podopiecznych obecnie zalogowanego opiekuna.
     * @return Lista podejść wszystkich uczniów obecnie zalogowanego opiekuna.
     * @throws ApplicationBaseException Jeżeli np. nie uda się odnaleźć encji obecnie zalogowanego opiekuna.
     */
    List<ApproachEntity> listAllForGuardian() throws ApplicationBaseException;

    ApproachEntity findById(long id) throws ApplicationBaseException;

    /**
     * @return Metoda zwraca listę egzaminów do których może przystąpić student
     * @throws ApplicationBaseException
     */
    List<ExamEntity> findAvailableExams() throws ApplicationBaseException;
}
