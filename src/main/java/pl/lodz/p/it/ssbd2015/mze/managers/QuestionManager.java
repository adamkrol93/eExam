package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.managers.QuestionManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class QuestionManager implements QuestionsManagerLocal {

	@EJB
	private QuestionEntityFacadeLocal questionEntityFacade;

	@EJB
	private ExaminerEntityFacadeLocal examinerEntityFacade;

	@Override
	@RolesAllowed("CREATE_QUESTION_MZE")
	public void createQuestion(QuestionEntity question) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("EDIT_QUESTION_MZE")
	public void editQuestion(QuestionEntity question, QuestionEntity newQuestion) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
