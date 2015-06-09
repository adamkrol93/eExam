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

    /**
     * Wykorzystywana pomocniczo w przypadku edycji egzaminu.
     * Wyszukuje i ustawia egzamin do edycji
     * @param examId id szukanego egzaminu
     * @return Encja odnalezionego egzaminu
     * @throws ApplicationBaseException Rzucany, gdy w bazie nie ma egzaminu o podanym id
     */
    ExamEntity findById(long examId) throws ApplicationBaseException;

    /**
     * Pobiera wszystkich nauczycieli którzy nie są przypisani do danego egzaminu. Wykorzystywana do dodania nauczyciela do egzaminu.
     * @return Zwraca listę nauczycieli
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony egzamin do którego należy znaleźć nauczycieli.
     */
    List<TeacherEntity> findAllNotInExam() throws ApplicationBaseException;

    /**
     * Egzaminator edytuje właściwości należące bezpośrednio do egzaminu, takie jak nazwa, liczba pytań,
     * liczba dostępnych podejść, czas trwania podejścia, daty rozpoczęcia i zakończenia.
     * Aby edytować egzamin należy go najpierw wczytać, wykorzystując metodę EditExamServiceRemote.findById()
     * @param exam Encja reprezentująca egzamin po wprowadzeniu zmian.
     * @throws ApplicationBaseException Rzucany, kiedy daty rozpoczęcia i zakończenia egzaminu nie pozostają w
     * poprawnym stosunku lub gdy nie zostanie znaleziony egzaminator edytujący egzamin.
     */
    void editExam(ExamEntity exam) throws ApplicationBaseException;

    /**
     * Pozwala na przypisanie nauczyciela do egzaminu. Nauczyciel może dzięki temu oceniać podejścia do tego egzaminu.
     * @param teacherId Klucz główny nauczyciela, który zostanie dodany do egzaminu.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony nauczyciel o podanym id na liscie nauczycieli nie przypisanych do tego egzaminu.
     */
    void addTeacher(long teacherId) throws ApplicationBaseException;

    /**
     * Usuwa pytanie z egzaminu, jeżeli nie istnieją jeszcze podejścia do niego. Kończy się wyjątkiem
     * ExamApproachesExistException jeżeli istnieją już do niego podejścia. Jeżeli trafi do tej metody jakiś losowy
     * id, któremu nie odpowiada żadne pytanie w podesłanym egzaminie, to też rzuca wyjątek.
     * Po wszystkim oznacza egzamin jako zmodyfikowany, zwiększając przy tym wartość w polu wersji tego egzaminu.
     * To samo zachodzi w przypadku podejścia do egzaminu, więc ewentualne utworzenie podejścia w czasie wykonywania
     * tego przypadku użycia zostanie wykryte, choć dopiero przy sprawdzaniu pola wersji, bo tego nowego podejścia
     * nie bedzie w kolekcji sprawdzanej na początku, co skutkowało ExamApproachesExistException.
     * @param questionId Klucz główny pytania, które zostanie usunięte.
     * @throws ApplicationBaseException Rzucany, gdy nie znajdziemy egzaminatora, gdy podejścia do egzaminu już
     * istnieją lub gdy id pytania nie pozwoli odnaleźć encji.
     */
    void removeQuestion(long questionId) throws ApplicationBaseException;

    /**
     * Egzaminator usuwa nauczyciela z egzaminu.
     * @param teacherId Klucz główny nauczyciela, który zostanie usunięty.
     * @throws ApplicationBaseException Rzucany, gdy nie zostanie znaleziony obecnie zalogowany nauczyciel.
     */
    void removeTeacher(long teacherId) throws ApplicationBaseException;

}
