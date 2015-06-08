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

    /**
     * Metoda odpowiada za utworzenie pytania
     * @param question Encja, którą chcemy utworzyć
     * @throws ApplicationBaseException Rzucany, kiedy nie uda się utworzyć pytania
     */
    void createQuestion(QuestionEntity question) throws ApplicationBaseException;

    /**
     * Edycja pytania, które może zostać później wykorzystane podczas tworzenia egzaminu.
     * Jeśli pytanie było wykorzystane już w egzaminie który miał podejście,
     * tworzone jest nowe pytanie będącego kopią edytowanego wraz z uwzględnieniem edytowanych wartości.
     * @param question Pytanie edytowane
     * @param newQuestion Pytanie zawierące zmiany
     * @return Id encji
     * @throws ApplicationBaseException Rzucany, kiedy nie uda się zedytować/utworzyć encji
     */
    long editQuestion(QuestionEntity question, QuestionEntity newQuestion) throws ApplicationBaseException;
}
