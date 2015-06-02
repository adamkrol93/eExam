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
     * @throws ApplicationBaseException
     */
    void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException;

    /**
     * Metoda pozwala zdyskwalifikować podejście przez Nauczyciela
     * @param approach Podejście które ma zostać zdyskwalifikowane
     * @throws ApplicationBaseException
     */
    void disqualify(ApproachEntity approach) throws ApplicationBaseException;

    /**
     * Metoda pozwala pobrać wszystkie egzaminy do których przypisany jest aktualnie zalogowany nauczyciel.
     * @return lista egzaminów nauczyciela
     * @throws ApplicationBaseException
     */
    List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException;

    /**
     * Metoda pozwala ustawić studentowi jego opiekuna
     * @param guardian Nowy opiekun dla studenta
     * @param student Student któremu chcemy ustawić opiekuna
     * @throws ApplicationBaseException
     */
    void connect(GuardianEntity guardian, StudentEntity student) throws ApplicationBaseException;

    /**
     * Metoda pozwala no obliczenie i ustawienie w egzaminie agregatów
     * @param examEntity egzamin do policzenia i ustawienia agregat
     */
    void agragete(ExamEntity examEntity);
}
