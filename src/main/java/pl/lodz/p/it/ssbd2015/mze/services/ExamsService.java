package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ExamsService implements ExamsServiceRemote {

	private QuestionsManagerLocal questionsManager;

	private ExamEntityFacadeLocal examEntityFacade;

	private TeacherEntityFacadeLocal teacherEntityFacade;

	private QuestionEntityFacadeLocal questionEntityFacade;

	@Override
	public ExamEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void create(QuestionEntity questionEntity) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<QuestionEntity> findAllQuestions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TeacherEntity> findAllTeachers() {
		throw new UnsupportedOperationException();
	}
}
