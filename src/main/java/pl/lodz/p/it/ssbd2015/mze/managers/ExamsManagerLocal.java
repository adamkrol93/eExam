package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface ExamsManagerLocal {

	void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers);

	void cloneExam(ExamEntity exam);

	List<TeacherEntity> findAllNotInExam(ExamEntity exam);

	void editExam(ExamEntity exam, ExamEntity newExam);

	void addTeacher(ExamEntity exam, TeacherEntity teacher);

	void removeQuestion(ExamEntity exam, long questionId);

	void removeTeacher(ExamEntity exam, long teacherId);
}
