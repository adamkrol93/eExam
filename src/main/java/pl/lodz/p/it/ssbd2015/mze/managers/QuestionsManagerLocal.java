package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;

/**
 * Interfejs do tworzenia i edycji pytań w systemie.
 * @author Bartosz Ignaczewski
 */
@Local
public interface QuestionsManagerLocal {

    void createQuestion(QuestionEntity question) throws ApplicationBaseException;

    void editQuestion(QuestionEntity question, QuestionEntity newQuestion) throws ApplicationBaseException;
}
