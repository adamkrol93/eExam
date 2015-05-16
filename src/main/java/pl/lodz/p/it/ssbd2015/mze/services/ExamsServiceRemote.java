package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs wykorzystywany do tworzenia pytań, wyszkiwania egzaminów, pytań i nauczycieli.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ExamsServiceRemote {

    ExamEntity findById(long id) throws ApplicationBaseException;

    void create(QuestionEntity questionEntity) throws ApplicationBaseException;

    List<QuestionEntity> findAllQuestions();

    List<TeacherEntity> findAllTeachers();

}
