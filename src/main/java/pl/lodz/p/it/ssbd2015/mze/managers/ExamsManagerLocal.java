package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfejs służący do zarządzania Egzaminami. Pozwala na tworzenie, klonowanie, edycję.
 * @author Bartosz Ignaczewski
 */
@Local
public interface ExamsManagerLocal {

    /**
     * Tworzy nowy egzamin, wziążąc z nim od razu wskazane pytania i nauczycieli.
     * @param exam Egzamin, który ma zostać utrwalony.
     * @param questions Pytania, z którymi ma być na początku związany egzamin.
     * @param teachers Nauczyciele, z którymi ma być na początku związany egzamin.
     * @throws ApplicationBaseException Jeżeli coś będzie nie tak z danymi.
     */
    void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers) throws ApplicationBaseException;

    void cloneExam(ExamEntity exam) throws ApplicationBaseException;

    List<TeacherEntity> findAllNotInExam(ExamEntity exam) throws ApplicationBaseException;

    void editExam(ExamEntity exam, ExamEntity newExam) throws ApplicationBaseException;

    void addTeacher(ExamEntity exam, TeacherEntity teacher) throws ApplicationBaseException;

    /**
     * Usuwa pytanie z egzaminu, jeżeli nie istnieją jeszcze podejścia do niego.
     * @param exam Egzamin, z którego zostanie usunięte pytanie.
     * @param questionId Klucz główny pytania, które zostanie usunięte.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony obecnie zalogowany egzaminator.
     */
    void removeQuestion(ExamEntity exam, long questionId) throws ApplicationBaseException;

    void removeTeacher(ExamEntity exam, long teacherId) throws ApplicationBaseException;
}
