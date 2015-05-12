package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ExamCreationService implements ExamCreationServiceRemote {

	@EJB
	private QuestionsManagerLocal questionsManager;

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
