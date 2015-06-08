package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;

/**
 * Interfejs wykorzsytywanay do edycji pytania w systemie
 * @author Bartosz Ignaczewski
 */
@Remote
public interface EditQuestionServiceRemote {

    /**
     * Wyszukuje w bazie encje o podanym id
     * @param id identyfikator, którego szukamy
     * @return QuestionEntity jeżeli coś znajdzie
     * @throws ApplicationBaseException Rzucany, kiedy nie znajdzie encji w bazie
     */
    QuestionEntity findById(long id) throws ApplicationBaseException;

    /**
     * Egzaminator edytuje pytanie, które może zostać później wykorzystane podczas tworzenia egzaminu.
     * Jeśli pytanie było wykorzystane już w egzaminie który miał podejście, tworzone jest nowe pytanie
     * będącego kopią edytowanego wraz z uwzględnieniem edytowanych wartości. Aby zedytować dane pytanie,
     * musimy je znaleźć przez EditQuestionServiceRemote.findById(), następnie przekazać do
     * EditQuestionServiceRemote.editQuestion()
     * @param question Encja, którą chcemy edytować
     * @return id encji, którą edytowaliśmy
     * @throws ApplicationBaseException Rzucany, kiedy nie zedytuje pytania
     */
    long editQuestion(QuestionEntity question) throws ApplicationBaseException;

}
