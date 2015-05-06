package pl.lodz.p.it.ssbd2015.mre.managers;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface AnswersManagerLocal {

	Long createApproach(String title) throws ApplicationBaseException;

	void editApproach(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException;

	void endApproach(ApproachEntity approach) throws ApplicationBaseException;

	List<ExamEntity> findAvailableExams();

}
