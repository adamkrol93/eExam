package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface EditExamServiceRemote {

	ExamEntity findById(long examId) throws ApplicationBaseException;

	List<TeacherEntity> findAllNotInExam();

	void editExam(ExamEntity exam) throws ApplicationBaseException;

	void addTeacher(long teacherId) throws ApplicationBaseException;

	void removeQuestion(long questionId) throws ApplicationBaseException;

	void removeTeacher(long teacherId) throws ApplicationBaseException;

}