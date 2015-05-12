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

	List<ApproachEntity> findAllForStudent() throws ApplicationBaseException;

	List<ApproachEntity> findAllForGuardian() throws ApplicationBaseException;

	ApproachEntity findById(long id) throws ApplicationBaseException;

	List<ExamEntity> findAvailableExams() throws ApplicationBaseException;

}
