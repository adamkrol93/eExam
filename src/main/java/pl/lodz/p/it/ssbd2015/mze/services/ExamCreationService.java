package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamEndBeforeStartException;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klasa do zarządzania tworzeniem egzaminów.
 * Klasa posiada pola questions oraz teachers.
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mze.services.ExamCreationService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ExamCreationService extends BaseStatefulService implements ExamCreationServiceRemote {

    @EJB
    private QuestionEntityFacadeLocal questionEntityFacade;

    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @EJB
    private ExamsManagerLocal examsManager;

    private List<QuestionEntity> questions;

    private List<TeacherEntity> teachers;

    @Override
    @RolesAllowed("CREATE_EXAM_MZE")
    public List<QuestionEntity> findAllQuestions() {
    	questions = questionEntityFacade.findAll();
    	return questions;
    }

    @Override
    @RolesAllowed("CREATE_EXAM_MZE")
    public List<TeacherEntity> findAllTeachers() {
        teachers = teacherEntityFacade.findAll().stream()
                .filter(teacher -> teacher.isActive() && teacher.isConfirm() && teacher.isGroupActive())
                .collect(Collectors.toList());
    	return teachers;
    }

    @Override
    @RolesAllowed("CREATE_EXAM_MZE")
    public void create(ExamEntity exam, List<Long> questionIds, List<Long> teacherIds) throws ApplicationBaseException {
        if (exam.getDateStart() != null && exam.getDateEnd() != null && !exam.getDateEnd().after(exam.getDateStart())) {
            throw new ExamEndBeforeStartException("End date to " + exam.getDateEnd() + " which is before start date " + exam.getDateStart());
        }

        ExamEntity newExam = new ExamEntity();
    	newExam.setTitle(exam.getTitle());
    	newExam.setCountTakeExam(exam.getCountTakeExam());
    	newExam.setCountQuestion(exam.getCountQuestion());
    	newExam.setDateEnd(exam.getDateEnd());
    	newExam.setDateStart(exam.getDateStart());
    	newExam.setDuration(exam.getDuration());

    	Set<Long> questionIdSet = new HashSet<>(questionIds);
    	Set<Long> teacherIdSet = new HashSet<>(teacherIds);

    	List<QuestionEntity> chosenQuestions = new ArrayList<>(questions);
    	List<TeacherEntity> chosenTeachers = new ArrayList<>(teachers);
    	chosenQuestions.removeIf(question -> !questionIdSet.contains(question.getId()));
    	chosenTeachers.removeIf(teacher -> !teacherIdSet.contains(teacher.getId()));

    	examsManager.createExam(exam, chosenQuestions, chosenTeachers);
    }
}
