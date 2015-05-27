package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExaminerNotFoundException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacadeLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Iterator;
import java.util.List;

import static pl.lodz.p.it.ssbd2015.utils.ExceptionUtils.elvis;

/**
 * Implementacja interfejsu {@link ExamsManagerLocal}, pozwala na zarządzanie Egzaminami
 * @author Andrzej Kurczewski
 * @author Bartosz Ignaczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.managers.ExamsManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamsManager implements ExamsManagerLocal {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @EJB
    private ExaminerEntityFacadeLocal examinerEntityFacade;

    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("CREATE_EXAM_MZE")
    public void createExam(ExamEntity exam, List<QuestionEntity> questions, List<TeacherEntity> teachers) throws ApplicationBaseException {
    	String name = elvis(() -> sessionContext.getCallerPrincipal().getName());
    	ExaminerEntity examiner = examinerEntityFacade.findByLogin(name)
    			.orElseThrow(() -> new ExaminerNotFoundException("Could not find ExaminerEntity for logged user " + name));
    	exam.setCreator(examiner);

    	questions.forEach(question -> {
    		exam.getQuestions().add(question);
    		question.getExams().add(exam);
    	});
    	teachers.forEach(teacher -> {
    		exam.getTeachers().add(teacher);
    		teacher.getExams().add(exam);
    	});

    	examEntityFacade.create(exam);
    }

    @Override
    @RolesAllowed("CLONE_EXAM_MZE")
    public void cloneExam(ExamEntity exam) throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();
        ExaminerEntity examiner = examinerEntityFacade.findByLogin(login).orElseThrow(() -> new ExaminerNotFoundException("Examiner with login " + login + "does not exist"));

        ExamEntity newExam = new ExamEntity();
        newExam.setCreator(examiner);
        newExam.setCountTakeExam(exam.getCountTakeExam());
        newExam.setCountQuestion(exam.getCountQuestion());
        newExam.setDuration(exam.getDuration());
        newExam.setDateStart(exam.getDateStart());
        newExam.setDateEnd(exam.getDateEnd());
        newExam.setQuestions(exam.getQuestions());
        newExam.setTeachers(exam.getTeachers());
        newExam.setTitle(exam.getTitle() + " kopia");

        examEntityFacade.create(newExam);
    }

    @Override
    @RolesAllowed("ADD_TEACHER_TO_EXAM_MZE")
    public List<TeacherEntity> findAllNotInExam(ExamEntity exam) throws ApplicationBaseException {
    	return teacherEntityFacade.findAllNotInExam(exam);
    }

    @Override
    @RolesAllowed("EDIT_EXAM_MZE")
    public void editExam(ExamEntity exam, ExamEntity newExam) throws ApplicationBaseException {
    	exam.setCountQuestion(newExam.getCountQuestion());
        exam.setCountTakeExam(newExam.getCountTakeExam());
        exam.setDateStart(newExam.getDateStart());
        exam.setDateEnd(newExam.getDateEnd());
        exam.setDuration(newExam.getDuration());
        exam.setTitle(newExam.getTitle());

        String login = sessionContext.getCallerPrincipal().getName();
        ExaminerEntity modifier = examinerEntityFacade.findByLogin(login).orElseThrow(() -> new ExaminerNotFoundException("Examiner with login " + login + "does not exist"));
        exam.setModifier(modifier);

        examEntityFacade.edit(exam);
    }

    @Override
    @RolesAllowed("ADD_TEACHER_TO_EXAM_MZE")
    public void addTeacher(ExamEntity exam, TeacherEntity teacher) throws ApplicationBaseException {
        exam.getTeachers().add(teacher);

        examEntityFacade.edit(exam);
    }

    @Override
    @RolesAllowed("REMOVE_QUESTION_FROM_EXAM_MZE")
    public void removeQuestion(ExamEntity exam, long questionId) throws ApplicationBaseException {
        boolean found = false;

        for (Iterator<QuestionEntity> it = exam.getQuestions().iterator(); it.hasNext();) {
            QuestionEntity question = it.next();
            if (question.getId() == questionId) {
                it.remove();
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Attempt to remove Question with id = " + questionId
                    + " from exam " + exam + " failed because it didn't have this question.");
        }

        String login = sessionContext.getCallerPrincipal().getName();
        ExaminerEntity modifier = examinerEntityFacade.findByLogin(login)
                .orElseThrow(() -> new ExaminerNotFoundException("Examiner with login " + login + "does not exist"));
        exam.setModifier(modifier);

        examEntityFacade.edit(exam);
    }

    @Override
    @RolesAllowed("REMOVE_TEACHER_FROM_EXAM_MZE")
    public void removeTeacher(ExamEntity exam, long teacherId) throws ApplicationBaseException {
    	throw new UnsupportedOperationException();
    }
}
