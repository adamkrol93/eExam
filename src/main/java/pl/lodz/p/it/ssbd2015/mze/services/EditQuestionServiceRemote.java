package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.ejb.Remote;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Remote
public interface EditQuestionServiceRemote {

	QuestionEntity findById(long id) throws ApplicationBaseException;

	void editQuestion(QuestionEntity question) throws ApplicationBaseException;

}
