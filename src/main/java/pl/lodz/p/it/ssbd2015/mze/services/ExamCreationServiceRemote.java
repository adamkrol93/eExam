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

	List<QuestionEntity> findAllQuestions();

	List<TeacherEntity> findAllTeachers();

	void create(ExamEntity exam, List<Long> questions, List<Long> teachers) throws ApplicationBaseException;

}
