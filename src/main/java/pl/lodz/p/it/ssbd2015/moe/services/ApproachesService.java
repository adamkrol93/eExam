package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManagerLocal;

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
public class ApproachesService implements ApproachesServiceRemote {

	@EJB
	private ApproachesManagerLocal approachesManager;

	@EJB
	private ApproachEntityFacadeLocal approachEntityFacade;

	@Override
	@RolesAllowed("SHOW_APPROACH_MOE")
	public ApproachEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("LIST_APPROACHES_MOE")
	public List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
