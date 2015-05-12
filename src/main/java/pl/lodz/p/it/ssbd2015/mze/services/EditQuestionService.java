package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EditQuestionService implements EditQuestionServiceRemote {

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
