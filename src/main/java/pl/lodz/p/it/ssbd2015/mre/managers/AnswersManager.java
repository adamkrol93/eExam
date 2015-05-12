package pl.lodz.p.it.ssbd2015.mre.managers;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AnswersManager implements AnswersManagerLocal {

	@EJB
	private ApproachEntityFacadeLocal approachEntityFacade;

	@EJB
	private ExamEntityFacadeLocal examEntityFacade;

	@EJB
	private StudentEntityFacadeLocal studentEntityFacade;

	@Override
	@RolesAllowed("START_SOLVING_EXAM_MRE")
	public Long createApproach(String title) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("ANSWER_QUESTION_MRE")
	public void editApproach(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("END_APPROACH_MRE")
	public void endApproach(ApproachEntity approach) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("LIST_AVAILABLE_EXAMS")
	public List<ExamEntity> findAvailableExams() {
		throw new UnsupportedOperationException();
	}
}
