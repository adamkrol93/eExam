package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GuardianStudentService implements GuardianStudentServiceRemote {

	private ApproachesManagerLocal approachesManager;

	private GuardianEntityFacadeLocal guardianEntityFacade;

	private StudentEntityFacadeLocal studentEntityFacade;

	private List<GuardianEntity> guardians;

	private List<StudentEntity> students;

	@Override
	public List<GuardianEntity> findAllGuardians() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<StudentEntity> findAllStudents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void connect(long guardianId, long studentId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
