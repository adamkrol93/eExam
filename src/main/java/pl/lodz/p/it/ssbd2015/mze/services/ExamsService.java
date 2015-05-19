package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.QuestionsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Klasa pozwalająca na wyszukiwanie pytań, nauczycieli oraz tworzenie pytań.
 * Klasa nie posiada dodatkowych pól
 *
 * @author Bartosz Ignaczewski
 * @author Piotr Jurewicz
 * @author Andrzej Kurczewski
 * @author Tobiasz Kowalski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mze.services.ExamsService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ExamsService extends BaseStatefulService implements ExamsServiceRemote {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @EJB
    private QuestionEntityFacadeLocal questionEntityFacade;

    @EJB
    private QuestionsManagerLocal questionsManager;

    @Override
    @RolesAllowed({"SHOW_EXAM_MZE", "SHOW_EXAM_STATS_MZE"})
    public ExamEntity findById(long id) throws ApplicationBaseException {
        ExamEntity exam = examEntityFacade.findById(id).orElseThrow(() -> new ExamNotFoundException("Exam with id: " + id + " does not exists"));
        exam.getTeachers().size();
        exam.getQuestions().size();
        return exam;
    }

    @Override
    @RolesAllowed("CREATE_QUESTION_MZE")
    public void create(QuestionEntity questionEntity) throws ApplicationBaseException {
        questionsManager.createQuestion(questionEntity);
    }

    @Override
    @RolesAllowed("LIST_QUESTIONS_MZE")
    public List<QuestionEntity> findAllQuestions() {
        return questionEntityFacade.findAll();
    }

    @Override
    @RolesAllowed("SHOW_TEACHER_LIST_MZE")
    public List<TeacherEntity> findAllTeachers() {
        throw new UnsupportedOperationException();
    }
}
