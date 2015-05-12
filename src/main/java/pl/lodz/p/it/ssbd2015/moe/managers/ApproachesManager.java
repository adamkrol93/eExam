package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.ExamEntityFacade;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeLocal;

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
public class ApproachesManager implements ApproachesManagerLocal {

	@EJB
	private ApproachEntityFacadeLocal approachEntityFacade;

	@EJB
	private ExamEntityFacade examEntityFacade;

	@EJB
	private TeacherEntityFacadeLocal teacherEntityFacade;

	@EJB
	private StudentEntityFacadeLocal studentEntityFacade;

	@Override
	@RolesAllowed("MARK_APPROACH_MOE")
	public void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("DISQUALIFY_APPROACH_MOE")
	public void disqualify(ApproachEntity approach) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("LIST_APPROACHES_MOE")
	public List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	@RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
	public void connect(GuardianEntity guardian, StudentEntity student) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
