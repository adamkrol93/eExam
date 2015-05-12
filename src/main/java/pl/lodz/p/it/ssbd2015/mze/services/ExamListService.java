package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;

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
public class ExamListService implements ExamListServiceRemote {

	@EJB
	private ExamEntityFacadeLocal examEntityFacade;

	@EJB
	private ExamsManagerLocal examsManager;

	private List<ExamEntity> exams;

	@Override
	@RolesAllowed("LIST_EXAMS_MZE")
	public List<ExamEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("CLONE_EXAM_MZE")
	public void cloneExam(long examId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
