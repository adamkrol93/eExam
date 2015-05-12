package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Klasa pozwalająca dokonywać ocen i dyskwalifikacji podejść.
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.moe.services.MarkApproachService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class MarkApproachService extends BaseStatefulService implements MarkApproachServiceRemote {

	@EJB
	private ApproachesManagerLocal approachesManager;

	@EJB
	private ApproachEntityFacadeLocal approachEntityFacade;

	private ApproachEntity approach;

	@Override
	@RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
	public ApproachEntity findById(long id) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("MARK_APPROACH_MOE")
	public void mark(List<AnswerEntity> gradedAnswers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("DISQUALIFY_APPROACH_MOE")
	public void disqualify() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
