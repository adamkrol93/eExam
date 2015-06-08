package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejst pozwalający na zarządzanie egzaminem tzn. dodawaniem/usuwaniem nauczycieli, oraz usuwaniem pytań.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface EditExamServiceRemote {

    ExamEntity findById(long examId) throws ApplicationBaseException;

    /**
     * Pobiera wszystkich nauczycieli którzy nie są przypisani do danego egzaminu. Wykorzystywana do dodania nauczyciela do egzaminu.
     * @return Zwraca listę nauczycieli
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony egzamin do którego należy znaleźć nauczycieli.
     */
    List<TeacherEntity> findAllNotInExam() throws ApplicationBaseException;

    void editExam(ExamEntity exam) throws ApplicationBaseException;

    /**
     * Pozwala na przypisanie nauczyciela do egzaminu. Nauczyciel może dzięki temu oceniać podejścia do tego egzaminu.
     * @param teacherId Klucz główny nauczyciela, który zostanie dodany do egzaminu.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony nauczyciel o podanym id na liscie nauczycieli nie przypisanych do tego egzaminu.
     */
    void addTeacher(long teacherId) throws ApplicationBaseException;

    /**
     * Usuwa pytanie z egzaminu, jeżeli nie istnieją jeszcze podejścia do niego.
     * Pytanie jest usuwane z obecnie edytowanego egzaminu.
     * @param questionId Klucz główny pytania, które zostanie usunięte.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony obecnie zalogowany egzaminator.
     */
    void removeQuestion(long questionId) throws ApplicationBaseException;
    /**
     * Usuwa nauczyciela z egzaminu.
     * Egzaminator usuwa nauczyciela z egzaminu.
     * @param teacherId Klucz główny nauczyciela, który zostanie usunięty.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony obecnie zalogowany nauczyciel.
     */
    void removeTeacher(long teacherId) throws ApplicationBaseException;

}