package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Klasa do zarządzania podejściami juz rozwiązanymi. Klasa nie posiada żadnego pola encyjnego.
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mre.services.ApproachesService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ApproachesService extends BaseStatefulService implements ApproachesServiceRemote {

	@EJB
	private AnswersManagerLocal answersManager;

	@EJB
	private ApproachEntityFacadeLocal approachEntityFacade;

	@EJB
	private StudentEntityFacadeLocal studentEntityFacade;

	@EJB
	private GuardianEntityFacadeLocal guardianEntityFacade;

	@Override
	@RolesAllowed("LIST_APPROACHES_MRE")
	public List<ApproachEntity> findAllForStudent() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("LIST_APPROACHES_MRE")
	public List<ApproachEntity> findAllForGuardian() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("SHOW_APPROACHES_MRE")
	public ApproachEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("LIST_AVAILABLE_EXAMS")
	public List<ExamEntity> findAvailableExams() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
