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
public interface ExamCreationServiceRemote {

	List<QuestionEntity> findAllQuestions();

	List<TeacherEntity> findAllTeachers();

	void create(ExamEntity exam, List<Long> questions, List<Long> teachers);

}
