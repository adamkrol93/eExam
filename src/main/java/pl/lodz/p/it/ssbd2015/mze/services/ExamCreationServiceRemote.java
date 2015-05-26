package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs, którego implementacja pozwala na wyszukanie wszystkich pytan oraz nauczycieli. Pozwala również na stworzenie egzaminu.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ExamCreationServiceRemote {

    /**
     * Zwraca listę wszystkich dostępnych pytań i ustawia je w beanie, by można było potem z nich utworzyć egzamin.
     * @return Lista dostępnych w systemie pytań.
     */
    List<QuestionEntity> findAllQuestions();

    /**
     * Zwraca listę nauczycieli w systemie i ustawia je w beanie, by można było potem z nich utworzyć egzamin.
     * @return Lista nauczycieli, którym można przypisać egzamin.
     */
    List<TeacherEntity> findAllTeachers();

    /**
     * Tworzy nowy egzamin, wziążąc z nim od razu wskazane pytania i nauczycieli.
     * Przekazywane są tylko klucze główne pytań i nauczycieli, więcej nie potrzeba, a byłoby to kosztowne przy
     * przesyłaniu zdalnym.
     * @param exam Obiekt, z którego część pól zostanie naniesiona na tworzony egzamin.
     * @param questions Idki pytań, z którymi ma być na początku związany egzamin.
     * @param teachers Idki nauczycieli, z którymi ma być na początku związany egzamin.
     * @throws ApplicationBaseException Jeżeli coś będzie nie tak z danymi.
     */
    void create(ExamEntity exam, List<Long> questions, List<Long> teachers) throws ApplicationBaseException;

}
