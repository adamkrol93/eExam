package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface AnswerServiceRemote {

	Long createApproach(String title) throws ApplicationBaseException;

	ApproachEntity findById(long approachId) throws ApplicationBaseException;

	void editApproach(List<AnswerEntity> answers) throws ApplicationBaseException;

	void endApproach() throws ApplicationBaseException;
}