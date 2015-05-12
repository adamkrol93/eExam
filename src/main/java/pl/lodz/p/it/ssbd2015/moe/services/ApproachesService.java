package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
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
 * @author  Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.moe.services.ApproachesService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ApproachesService extends BaseStatefulService implements ApproachesServiceRemote {

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
