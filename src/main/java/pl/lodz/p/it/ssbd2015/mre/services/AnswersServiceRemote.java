package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface AnswersServiceRemote {

	Long createApproach(String title);

	ApproachEntity findById(long approachId);

	void editAnswer(List<AnswerEntity> answers);

	void endApproach();
}