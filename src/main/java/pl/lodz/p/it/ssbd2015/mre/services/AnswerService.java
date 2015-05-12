package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

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
public class AnswerService implements AnswerServiceRemote {

	@EJB
	private AnswersManagerLocal answersManager;

	private ApproachEntity approach;

	@Override
	@RolesAllowed("START_SOLVING_EXAM_MRE")
	public Long createApproach(String title) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed({"ANSWER_QUESTION_MRE", "END_APPROACH_MRE"})
	public ApproachEntity findById(long approachId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("ANSWER_QUESTION_MRE")
	public void editApproach(List<AnswerEntity> answers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("END_APPROACH_MRE")
	public void endApproach() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}