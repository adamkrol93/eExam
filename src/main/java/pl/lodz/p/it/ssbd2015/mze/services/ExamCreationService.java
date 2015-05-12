package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mze.services.ExamCreationService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ExamCreationService extends BaseStatefulService implements ExamCreationServiceRemote {

	@EJB
	private QuestionEntityFacadeLocal questionEntityFacade;

	@EJB
	private TeacherEntityFacadeLocal teacherEntityFacade;

	@EJB
	private ExamsManagerLocal examsManager;

	private List<QuestionEntity> questions;

	private List<TeacherEntity> teachers;

	@Override
	@RolesAllowed("CREATE_EXAM_MZE")
	public List<QuestionEntity> findAllQuestions() {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("CREATE_EXAM_MZE")
	public List<TeacherEntity> findAllTeachers() {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("CREATE_EXAM_MZE")
	public void create(ExamEntity exam, List<Long> questions, List<Long> teachers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
