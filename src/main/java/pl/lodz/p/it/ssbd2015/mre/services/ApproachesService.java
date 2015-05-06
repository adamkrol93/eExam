package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ApproachesService implements ApproachesServiceRemote {

	private AnswersManagerLocal answersManager;

	private ApproachEntityFacadeLocal approachEntityFacade;

	private StudentEntityFacadeLocal studentEntityFacade;

	private GuardianEntityFacadeLocal guardianEntityFacade;

	@Override
	public List<ApproachEntity> findAllForStudent() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ApproachEntity> findAllForGuardian() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ApproachEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExamEntity> findAvailableExams() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
