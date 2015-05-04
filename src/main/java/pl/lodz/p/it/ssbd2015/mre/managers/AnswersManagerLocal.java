package pl.lodz.p.it.ssbd2015.mre.managers;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface AnswersManagerLocal {

	Long createApproach(String title);

	void editApproach(ApproachEntity approach, List<AnswerEntity> answers);

	void endApproach(ApproachEntity approach);

	List<ExamEntity> findAvailableExams();

}
