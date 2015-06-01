package pl.lodz.p.it.ssbd2015.moe.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.TeacherNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.*;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.math.BigInteger;
import java.util.List;

/**
 * Klasa implementująca interfejs {@link ApproachesManagerLocal} w celach dostarczenia funckjonlaności potrzebnych w module MOE.
 *
 * @author Bartosz Ignaczewski
 * @author Piotr Jurewicz
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

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("MARK_APPROACH_MOE")
    public void mark(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();
        TeacherEntity teacherEntity = teacherEntityFacade.findByLogin(login).orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));

        ExamEntity examEntity = examEntityFacade.findById(approach.getExam().getId()).orElseThrow(() -> new ExamNotFoundException("Exam with id: " + approach.getExam().getId() + " does not exists"));

        Boolean isAllowed = examEntity.getTeachers().stream().anyMatch(t->t.getLogin()==login);
        if(!isAllowed){
            throw new TeacherNotFoundException("Teacher with login: " + login + " does not exist among authorized to check this exam.");
        }

        for (AnswerEntity answer : answers) {
            answer.setTeacher(teacherEntity);
        }
        approach.setAnswers(answers);
        approachEntityFacade.edit(approach);

        examEntity.setCountFinishExam(examEntity.getCountFinishExam()==null ? 1 : examEntity.getCountTakeExam()+1);

        long sumOfGrades=0;
        long sumFromApproaches=0;

        for(ApproachEntity actualApproach : examEntity.getApproaches()){
            if(!actualApproach.isDisqualification()){
                for(AnswerEntity actualAnswer : actualApproach.getAnswers()){
                    sumOfGrades += actualAnswer.getGrade();
                }
                sumFromApproaches+=sumOfGrades;
            }
        }

        examEntity.setAvgResults(BigInteger.valueOf(sumFromApproaches)
                .divide(BigInteger.valueOf(examEntity.getCountFinishExam())));

        examEntityFacade.edit(examEntity);
    }

    @Override
    @RolesAllowed("DISQUALIFY_APPROACH_MOE")
    public void disqualify(ApproachEntity approach) throws ApplicationBaseException {
        approach.setDisqualification(true);

        approachEntityFacade.edit(approach);

        ExamEntity exam = examEntityFacade.findById(approach.getExam().getId())
                .orElseThrow(() -> new ExamNotFoundException("Exam with id: " + approach.getExam().getId() + " does not exists"));

        exam.setCountFinishExam(exam.getCountFinishExam()==null ? 1 : exam.getCountTakeExam()+1);

        long sumOfGrades=0;
        long sumFromApproaches=0;

        for(ApproachEntity actualApproach : exam.getApproaches()){
            if(!actualApproach.isDisqualification()){
                for(AnswerEntity answer : actualApproach.getAnswers()){
                    sumOfGrades += answer.getGrade();
                }
                sumFromApproaches+=sumOfGrades;
            }
        }

        exam.setAvgResults(BigInteger.valueOf(sumFromApproaches)
                .divide(BigInteger.valueOf(exam.getCountFinishExam())));

        examEntityFacade.edit(exam);
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MOE")
    public List<ExamEntity> findAllByLoggedTeacher() throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();
        TeacherEntity teacherEntity = teacherEntityFacade.findByLogin(login).orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));
        for (ExamEntity examEntity : teacherEntity.getExams())
        {
            examEntity.getApproaches().isEmpty();
            for (ApproachEntity approachEntity : examEntity.getApproaches())
            {
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
}
