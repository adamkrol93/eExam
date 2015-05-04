package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.moe.facades.AnswerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeSerializableLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ApproachesManager implements ApproachesManagerLocal {

	private ApproachEntityFacadeLocal approachEntityFacade;

	private AnswerEntityFacadeLocal answerEntityFacade;

	private ExamEntityFacadeSerializableLocal examEntityFacade;

	private TeacherEntityFacadeLocal teacherEntityFacade;

	private StudentEntityFacadeLocal studentEntityFacade;

	@Override
	public void mark(ApproachEntity approach, List<AnswerEntity> answers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void disqualify(ApproachEntity approach) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<ExamEntity> findAllByLoggedTeacher() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void connect(GuardianEntity guardian, StudentEntity student) {
		throw new UnsupportedOperationException();
	}
}
