package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface EditExamServiceRemote {

	ExamEntity findById(long examId);

	List<TeacherEntity> findAllNotInExam();

	void editExam(ExamEntity exam);

	void addTeacher(long teacherId);

	void removeQuestion(long questionId);

	void removeTeacher(long teacherId);

}