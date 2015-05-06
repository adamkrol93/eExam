package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface MarkApproachServiceRemote {

	ApproachEntity findById(long id) throws ApplicationBaseException;

	void mark(List<AnswerEntity> gradedAnswers) throws ApplicationBaseException;

	void disqualify() throws ApplicationBaseException;

}
