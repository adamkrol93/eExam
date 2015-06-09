package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs beanów pozwalających na utworzenie i utrwalenie egzaminu. Zawiera też inne metody niezbędne w tym celu,
 * wyszukujące nauczycieli i pytania do powiązania z egzaminem.
 * @author Michał Sośnicki
 */
@Remote
public interface ExamCreationServiceRemote {

    /**
     * Zwraca listę wszystkich dostępnych pytań i ustawia je w polu w beanie, by można było potem z nich utworzyć egzamin.
     * @return Lista dostępnych w systemie pytań.
     */
    List<QuestionEntity> findAllQuestions();

    /**
     * Zwraca listę nauczycieli w systemie i ustawia je w polu w beanie, by można było potem z nich utworzyć egzamin.
     * @return Lista nauczycieli, którym można przypisać egzamin.
     */
    List<TeacherEntity> findAllTeachers();

    /**
     * Tworzy nowy egzamin, wiążąc z nim od razu wskazane pytania i nauczycieli.
     * Do pobrania nauczycieli oraz pytań korzysta z metod ExamCreationServiceRemote.findAllQuestion()
     * i ExamCreationServiceRemote.findAllTeachers(). Te metody muszą być wywołane przed create(),
     * a idki przekazane do create muszą być tymi ze zwróconych wcześniej kolekcji.
     * @param exam Obiekt, z którego część pól zostanie naniesiona na tworzony egzamin.
     * @param questions Idki pytań, z którymi ma być na początku związany egzamin.
     * @param teachers Idki nauczycieli, z którymi ma być na początku związany egzamin.
     * @throws ApplicationBaseException Jeżeli coś będzie nie tak z danymi.
     */
    void create(ExamEntity exam, List<Long> questions, List<Long> teachers) throws ApplicationBaseException;

}
