package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ExamsManager implements ExamsManagerLocal {

	private ExamEntityFacadeLocal examEntityFacade;

	private ExaminerEntityFacadeLocal examinerEntityFacade;

	@Override
	public void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void cloneExam(ExamEntity exam) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<TeacherEntity> findAllNotInExam(ExamEntity exam) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void editExam(ExamEntity exam, ExamEntity newExam) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addTeacher(ExamEntity exam, TeacherEntity teacher) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeQuestion(ExamEntity exam, long questionId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeTeacher(ExamEntity exam, long teacherId) {
		throw new UnsupportedOperationException();
	}
}
