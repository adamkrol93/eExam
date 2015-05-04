package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EditQuestionService implements EditQuestionServiceRemote {

	private QuestionsManagerLocal questionsManager;

	private QuestionEntityFacadeLocal questionEntityFacade;

	private QuestionEntity question;

	@Override
	public QuestionEntity findById(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editQuestion(QuestionEntity question) {
		throw new UnsupportedOperationException();
	}
}
