package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MarkApproachService implements MarkApproachServiceRemote {

	private ApproachesManagerLocal approachesManager;

	private ApproachEntityFacadeLocal approachEntityFacade;

	private ApproachEntity approach;

	@Override
	public ApproachEntity findById(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void mark(List<AnswerEntity> gradedAnswers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void disqualify() {
		throw new UnsupportedOperationException();
	}
}
