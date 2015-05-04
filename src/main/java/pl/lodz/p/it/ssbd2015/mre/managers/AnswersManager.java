package pl.lodz.p.it.ssbd2015.mre.managers;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.mre.facades.AnswerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;

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

	private AnswerEntityFacadeLocal answerEntityFacade;

	private ApproachEntityFacadeLocal approachEntityFacade;

	private ExamEntityFacadeLocal examEntityFacade;

	private StudentEntityFacadeLocal studentEntityFacade;

	@Override
	public Long createApproach(String title) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editApproach(ApproachEntity approach, List<AnswerEntity> answers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void endApproach(ApproachEntity approach) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExamEntity> findAvailableExams() {
		throw new UnsupportedOperationException();
	}
}
