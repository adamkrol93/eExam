package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EditExamService implements EditExamServiceRemote {

	private ExamsManagerLocal examsManager;

	private ExamEntityFacadeLocal examEntityFacade;

	private TeacherEntityFacadeLocal teacherEntityFacade;

	private ExamEntity exam;

	private List<TeacherEntity> teachersNotInExam;

	@Override
	public ExamEntity findById(long examId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TeacherEntity> findAllNotInExam() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editExam(ExamEntity exam) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTeacher(long teacherId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeQuestion(long questionId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeTeacher(long teacherId) throws ApplicationBaseException {
		throw new UnsupportedOperationException();
	}
}
