package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
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
public class ApproachesService implements ApproachesServiceRemote {

	private ApproachesManagerLocal approachesManager;

	private ApproachEntityFacadeLocal approachEntityFacade;

	@Override
	public ApproachEntity findById(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExamEntity> findAllByLoggedTeacher() {
		throw new UnsupportedOperationException();
	}
}
