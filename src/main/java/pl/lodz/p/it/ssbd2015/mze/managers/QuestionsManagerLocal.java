package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;

import javax.ejb.Local;

/**
 * Created by Bartosz Ignaczewski on 04.05.15.
 */
@Local
public interface QuestionsManagerLocal {

	void createQuestion(QuestionEntity question);

	void editQuestion(QuestionEntity question, QuestionEntity newQuestion);
}
