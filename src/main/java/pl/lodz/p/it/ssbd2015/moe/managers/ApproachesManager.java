package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.TeacherNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Calendar;
import java.util.List;

/**
 * Klasa implementująca interfejs {@link ApproachesManagerLocal} w celach dostarczenia funckjonlaności potrzebnych w module MOE.
 *
 * @author Bartosz Ignaczewski
 * @author Piotr Jurewicz
 * @author Tobiasz Kowalski
 * @author Michał Sośnicki
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ApproachesManager implements ApproachesManagerLocal {

    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @EJB
    private TeacherEntityFacadeLocal teacherEntityFacade;

    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;

    @EJB
    private ApproachesManagerLocal approachesManager;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("MARK_APPROACH_MOE")
    public void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException {
        ExamStatsEntity examEntity = examEntityFacade.findStatsById(approach.getExam().getId())
                .orElseThrow(() -> new ExamNotFoundException("ExamStats with id: " + approach.getExam().getId() + " does not exists"));
        examEntityFacade.lockPessimisticWrite(examEntity);

        String login = sessionContext.getCallerPrincipal().getName();
        TeacherEntity teacherEntity = teacherEntityFacade.findByLogin(login)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));

        boolean isAllowed = false;
        for (TeacherEntity teacher : examEntity.getTeachers()) {
            if (teacher.getLogin().equals(login)) {
                isAllowed = true;
                break;
            }
        }
        if (!isAllowed) {
            throw new TeacherNotFoundException("Teacher with login: " + login + " does not exist among authorized to check this exam.");
        }

        for (AnswerEntity answer : answers) {
            answer.setTeacher(teacherEntity);
        }
        approach.setAnswers(answers);

        approach.setDateModification(Calendar.getInstance());
        approachEntityFacade.edit(approach);

        approachesManager.aggregateStats(examEntity);

        examEntityFacade.editStats(examEntity);
    }

    @Override
    @RolesAllowed("DISQUALIFY_APPROACH_MOE")
    public void disqualify(ApproachEntity approach) throws ApplicationBaseException {
        ExamStatsEntity exam = examEntityFacade.findStatsById(approach.getExam().getId())
                .orElseThrow(() -> new ExamNotFoundException("ExamStats with id: " + approach.getExam().getId() + " does not exists"));
        examEntityFacade.lockPessimisticWrite(exam);

        approach.setDisqualification(true);

        approachEntityFacade.edit(approach);

        String login = sessionContext.getCallerPrincipal().getName();

        Boolean isAllowed = false;
        for (TeacherEntity teacher : exam.getTeachers()) {
            if (teacher.getLogin().equals(login)) {
                isAllowed = true;
                break;
            }
        }
        if (!isAllowed) {
            throw new TeacherNotFoundException("Teacher with login: " + login + " does not exist among authorized to check this exam.");
        }

        approachesManager.aggregateStats(exam);

        examEntityFacade.editStats(exam);
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MOE")
    public List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();
        TeacherEntity teacherEntity = teacherEntityFacade.findByLogin(login).orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));
        for (ExamEntity examEntity : teacherEntity.getExams()) {
            examEntity.getApproaches().isEmpty();
            for (ApproachEntity approachEntity : examEntity.getApproaches()) {
                approachEntity.getAnswers().isEmpty();
            }
            examEntity.getTeachers().isEmpty();
            examEntity.getTeacherStubs().isEmpty();
        }
        return teacherEntity.getExams();
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public void connect(GuardianEntity guardian, StudentEntity student) throws ApplicationBaseException {
        student.setGuardian(guardian);
        this.studentEntityFacade.edit(student);
    }

    @Override
    @RolesAllowed({"MARK_APPROACH_MOE", "DISQUALIFY_APPROACH_MOE"})
    public void aggregateStats(ExamStatsEntity exam) {
        long counted = examEntityFacade.countExamFinished(exam.getId());
        exam.setCountFinishExam(counted > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) counted);
        exam.setAvgResults(counted != 0 ? (double) examEntityFacade.sumApproachesGrades(exam.getId()) / exam.getCountFinishExam() : null);
    }
}
