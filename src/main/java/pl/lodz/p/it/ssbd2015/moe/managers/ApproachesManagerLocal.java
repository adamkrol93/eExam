package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface ApproachesManagerLocal {

	void mark(ApproachEntity approach, List<AnswerEntity> answers);

	void disqualify(ApproachEntity approach);

	List<ExamEntity> findAllByLoggedTeacher();

	void connect(GuardianEntity guardian, StudentEntity student);

}
