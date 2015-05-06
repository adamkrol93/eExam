package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface ExamsManagerLocal {

	void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers) throws ApplicationBaseException;

	void cloneExam(ExamEntity exam) throws ApplicationBaseException;

	List<TeacherEntity> findAllNotInExam(ExamEntity exam) throws ApplicationBaseException;

	void editExam(ExamEntity exam, ExamEntity newExam) throws ApplicationBaseException;

	void addTeacher(ExamEntity exam, TeacherEntity teacher) throws ApplicationBaseException;

	void removeQuestion(ExamEntity exam, long questionId) throws ApplicationBaseException;

	void removeTeacher(ExamEntity exam, long teacherId) throws ApplicationBaseException;
}
