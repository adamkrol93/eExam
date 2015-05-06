package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface ApproachesManagerLocal {

	void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException;

	void disqualify(ApproachEntity approach) throws ApplicationBaseException;

	List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException;

	void connect(GuardianEntity guardian, StudentEntity student) throws ApplicationBaseException;

}
