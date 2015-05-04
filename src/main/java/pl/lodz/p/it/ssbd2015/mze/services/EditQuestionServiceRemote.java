package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.Remote;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface EditQuestionServiceRemote {

	QuestionEntity findById(long id);

	void editQuestion(QuestionEntity question);

}
