package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.TeacherNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacadeLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Klasa implementująca interfejs {@link ApproachesManagerLocal} w celach dostarczenia funckjonlaności potrzebnych w module MOE.
 *
 * @author Bartosz Ignaczewski
 * @author Piotr Jurewicz
 * @author Tobiasz Kowalski
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
        String login = sessionContext.getCallerPrincipal().getName();
        TeacherEntity teacherEntity = teacherEntityFacade.findByLogin(login).orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));

//        ExamEntity examEntity = examEntityFacade.findById(approach.getExam().getId()).orElseThrow(() -> new ExamNotFoundException("Exam with id: " + approach.getExam().getId() + " does not exists"));
        ExamEntity examEntity = approach.getExam();
        boolean isAllowed = false;
        for (TeacherEntity teacher : examEntity.getTeachers()) {
            if (teacher.getLogin().equals(login)) {
                isAllowed = true;
            }
        }
        if (!isAllowed) {
            throw new TeacherNotFoundException("Teacher with login: " + login + " does not exist among authorized to check this exam.");
        }

        for (AnswerEntity answer : answers) {
            answer.setTeacher(teacherEntity);
        }
        approach.setAnswers(answers);
        approachEntityFacade.edit(approach);

        approachesManager.aggregateStats(examEntity);

        examEntityFacade.edit(examEntity);
    }

    @Override
    @RolesAllowed("DISQUALIFY_APPROACH_MOE")
    public void disqualify(ApproachEntity approach) throws ApplicationBaseException {
        approach.setDisqualification(true);

        approachEntityFacade.edit(approach);

//        ExamEntity exam = examEntityFacade.findById(approach.getExam().getId())
//                .orElseThrow(() -> new ExamNotFoundException("Exam with id: " + approach.getExam().getId() + " does not exists"));

        ExamEntity exam = approach.getExam();

        String login = sessionContext.getCallerPrincipal().getName();

        Boolean exist =false;

        for(TeacherEntity teacher : exam.getTeachers()){
            if(teacher.getLogin().equals(login)){
                exist=true;
            }
        }

        if(!exist){
            throw new TeacherNotFoundException("Teacher with login: " + login + " does not exists.");
        }

        approachesManager.aggregateStats(exam);

        examEntityFacade.edit(exam);
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
    public void aggregateStats(ExamEntity examEntity) {
        long counted = examEntityFacade.countExamFinished(examEntity);
        examEntity.setCountFinishExam(counted > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)counted);
        examEntity.setAvgResults(counted != 0 ? (double)examEntityFacade.sumApproachesGrades(examEntity) / examEntity.getCountFinishExam() : null);
    }
}
