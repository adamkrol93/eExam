package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Local;
import java.util.List;

/**
 * Interfejs obsługujący wszystkie operacje możliwe do wykonania w module MOE.
 * @author Bartosz Ignaczewski
 */
@Local
public interface ApproachesManagerLocal {

    /**
     * Metoda pozwala dokonać oceny podejścia, przez Nauczyciela
     * @param approach Podejście które jest ocenianie
     * @param answers Odpowiedzi na pytania z ustawioną oceną
     * @throws ApplicationBaseException Ruzcany, kiedy nie dokona oceny podejścia
     */
    void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException;

    /**
     * Pozwala na stwierdzenie niesamodzielnej pracy i dyskwalifikacje podejście
     * @param approach Podejście które ma zostać zdyskwalifikowane
     * @throws ApplicationBaseException Rzucany, kiedy dyskwalifikacja nie powiedzie się
     */
    void disqualify(ApproachEntity approach) throws ApplicationBaseException;

    /**
     * Metoda pozwala pobrać wszystkie egzaminy do których przypisany jest aktualnie zalogowany nauczyciel.
     * @return lista egzaminów nauczyciela
     * @throws ApplicationBaseException Rzucany, kiedy nie pobierze odpowiednich egzaminów
     */
    List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException;

    /**
     * Metoda pozwala ustawić studentowi jego opiekuna
     * @param guardian Nowy opiekun dla studenta
     * @param student Student któremu chcemy ustawić opiekuna
     * @throws ApplicationBaseException Rzucany, kiedy nie ustawi studentowi opiekuna
     */
    void connect(GuardianEntity guardian, StudentEntity student) throws ApplicationBaseException;

    /**
     * Metoda pozwala no obliczenie i ustawienie w egzaminie agregatów
     * @param exam egzamin do policzenia i ustawienia agregat
     */
    void aggregateStats(ExamStatsEntity exam);
}
