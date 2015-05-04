package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface ExamsServiceRemote {

	ExamEntity findById(long id);

	void create(QuestionEntity questionEntity);

	List<QuestionEntity> findAllQuestions();

	List<TeacherEntity> findAllTeachers();

}
