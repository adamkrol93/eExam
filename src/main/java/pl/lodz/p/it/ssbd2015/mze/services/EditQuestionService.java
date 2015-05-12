package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

/**
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mze.services.EditQuestionService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class EditQuestionService extends BaseStatefulService implements EditQuestionServiceRemote {

	@EJB
	private QuestionEntityFacadeLocal questionEntityFacade;

	@EJB
	private QuestionsManagerLocal questionsManager;

	private QuestionEntity question;

	@Override
	@RolesAllowed("EDIT_QUESTION_MZE")
	public QuestionEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("EDIT_QUESTION_MZE")
	public void editQuestion(QuestionEntity question) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
