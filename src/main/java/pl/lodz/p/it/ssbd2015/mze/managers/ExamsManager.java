package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * @author Bartosz Ignaczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.managers.ExamsManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamsManager implements ExamsManagerLocal {

	@EJB
	private ExamEntityFacadeLocal examEntityFacade;

	@EJB
	private ExaminerEntityFacadeLocal examinerEntityFacade;

	@Override
	@RolesAllowed("CREATE_EXAM_MZE")
	public void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("CLONE_EXAM_MZE")
	public void cloneExam(ExamEntity exam) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("ADD_TEACHER_TO_EXAM_MZE")
	public List<TeacherEntity> findAllNotInExam(ExamEntity exam) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("EDIT_EXAM_MZE")
	public void editExam(ExamEntity exam, ExamEntity newExam) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("ADD_TEACHER_TO_EXAM_MZE")
	public void addTeacher(ExamEntity exam, TeacherEntity teacher) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("REMOVE_QUESTION_FROM_EXAM_MZE")
	public void removeQuestion(ExamEntity exam, long questionId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("REMOVE_TEACHER_FROM_EXAM_MZE")
	public void removeTeacher(ExamEntity exam, long teacherId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
