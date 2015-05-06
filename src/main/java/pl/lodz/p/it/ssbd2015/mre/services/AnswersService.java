package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AnswersService implements AnswersServiceRemote {

	private AnswersManagerLocal answersManager;

	private ApproachEntity approach;

	@Override
	public Long createApproach(String title) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ApproachEntity findById(long approachId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editAnswer(List<AnswerEntity> answers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void endApproach() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}