package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface ApproachesServiceRemote {

	List<ApproachEntity> findAllForStudent();

	List<ApproachEntity> findAllForGuardian();

	ApproachEntity findById(long id);

	List<ExamEntity> findAvailableExams();

}
