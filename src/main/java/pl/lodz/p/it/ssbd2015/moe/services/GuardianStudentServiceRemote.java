package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface GuardianStudentServiceRemote {

	List<GuardianEntity> findAllGuardians();

	List<StudentEntity> findAllStudents();

	void connect(long guardianId, long studentId) throws ApplicationBaseException;

}
