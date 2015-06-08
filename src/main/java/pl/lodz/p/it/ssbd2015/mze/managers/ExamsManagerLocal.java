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

    /**
     * Klonuje egzamin
     * @param exam Egzamin, który klonuje
     * @throws ApplicationBaseException Rzucany, kiedy nie uda się sklonować egzaminu
     */
    void cloneExam(ExamEntity exam) throws ApplicationBaseException;

    /**
     * Znajduje listę wszystkich nauczycieli którzy nie brali udziału w egzaminie
     * @param exam Egzamin, który jest sprawdzany
     * @return Lista nauczycieli
     * @throws ApplicationBaseException Rzucany, kiedy wystąpi błąd przy szukaniu nauczycieli
     */
    List<TeacherEntity> findAllNotInExam(ExamEntity exam) throws ApplicationBaseException;

    /**
     * Edytuje egzamin
     * @param exam Egzamin edytowany
     * @param newExam Egzamin zawierajacy nowe informacje
     * @throws ApplicationBaseException Rzucany, jeśli edycja się nie uda
     */
    void editExam(ExamEntity exam, ExamEntity newExam) throws ApplicationBaseException;

    /**
     * Dodaje nauczyciela do egzaminu
     * @param exam Egzamin, do którego jest dodawany nauczyciel
     * @param teacher Nauczyciel, który jest dodawany do egzaminu
     * @throws ApplicationBaseException Rzucany, jeśli nie uda się dodać nauczyciela do egzaminu
     */
    void addTeacher(ExamEntity exam, TeacherEntity teacher) throws ApplicationBaseException;

    /**
     * Usuwa pytanie z egzaminu, jeżeli nie istnieją jeszcze podejścia do niego.
     * @param exam Egzamin, z którego zostanie usunięte pytanie.
     * @param questionId Klucz główny pytania, które zostanie usunięte.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony obecnie zalogowany egzaminator.
     */
    void removeQuestion(ExamEntity exam, long questionId) throws ApplicationBaseException;

    /**
     * Usuwa nauczyciela z egzaminu
     * @param exam Egzamin, z którego jest usuwany nauczyciel
     * @param teacherId Id nauczyciela który ma być usunięty z egzaminu
     * @throws ApplicationBaseException Rzucany, jeśli nie uda się usunąć nauczyciela z egzaminu
     */
    void removeTeacher(ExamEntity exam, long teacherId) throws ApplicationBaseException;
}
