package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface ApproachesServiceRemote {

	List<ApproachEntity> findAllForStudent() throws ApplicationBaseException;

	List<ApproachEntity> findAllForGuardian() throws ApplicationBaseException;

	ApproachEntity findById(long id) throws ApplicationBaseException;

	List<ExamEntity> findAvailableExams() throws ApplicationBaseException;

}
