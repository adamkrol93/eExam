package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class QuestionManager implements QuestionsManagerLocal {

	private QuestionEntityFacadeLocal questionEntityFacade;

	private ExaminerEntityFacadeLocal examinerEntityFacadeLocal;

	@Override
	public void createQuestion(QuestionEntity question) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editQuestion(QuestionEntity question, QuestionEntity newQuestion) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
