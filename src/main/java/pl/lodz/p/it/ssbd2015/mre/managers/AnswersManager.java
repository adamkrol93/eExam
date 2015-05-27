package pl.lodz.p.it.ssbd2015.mre.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.StudentNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.UnavailableExamException;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementacja interfejsu {@link AnswersManagerLocal} do do zarządzania podejściem oraz dostarcza listę podejść do egzaminu.
 * Pozwala na operacje rozpoczęcia, edycji oraz zakończenia podejścia.
 *
 * @author Bartosz Ignaczewski
 * @author Piotr Jurewicz
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.managers.AnswersManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class AnswersManager implements AnswersManagerLocal {

    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public Long createApproach(String title) throws ApplicationBaseException {
        ExamEntity examEntity = examEntityFacade.findByTitle(title).orElseThrow(() -> new ExamNotFoundException("Approach titled: \"" + title + "\" does not exists"));

        String login = sessionContext.getCallerPrincipal().getName();
        StudentEntity studentEntity = studentEntityFacade.findByLogin(login).orElseThrow(() -> new StudentNotFoundException("Student with login: " + login + " does not exists"));

        if(examEntity.getCountTakeExam() <= examEntity.getApproaches().stream().filter(s -> s.getEntrant() == studentEntity).collect(Collectors.toList()).size()){
            throw new UnavailableExamException("Exceeded the allowed amount of approaches for student with login: "+login);
        }
        if(examEntity.getDateStart().after(Calendar.getInstance())){
            throw new UnavailableExamException("This exam has not yet started");
        }
        if(examEntity.getDateEnd().before(Calendar.getInstance())){
            throw new UnavailableExamException("This exam has already finished");
        }

        ApproachEntity approachEntity = new ApproachEntity();
        approachEntity.setEntrant(studentEntity);
        approachEntity.setExam(examEntity);
        approachEntity.setDateStart(Calendar.getInstance());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, examEntity.getDuration());
        approachEntity.setDateEnd(cal);
        approachEntity.setDisqualification(false);
        List <QuestionEntity> shuffledQuestions = new ArrayList(examEntity.getQuestions());
        List<AnswerEntity> answers = new ArrayList<>();
        int i=0;
        do{
            long seed = System.nanoTime();
            Collections.shuffle(shuffledQuestions, new Random(seed));
            int j = 0;
            while(i < examEntity.getCountQuestion() && j<shuffledQuestions.size()){
                QuestionEntity question = shuffledQuestions.get(j);
                AnswerEntity answer = new AnswerEntity();
                answer.setQuestion(question);
                answer.setApproach(approachEntity);
                answer.setGrade(0);
                answer.setContent("");
                answers.add(answer);
                i++;
                j++;
            }
        }while(i<examEntity.getCountQuestion());
        approachEntity.setAnswers(answers);
        approachEntityFacade.create(approachEntity);

        return approachEntity.getId();
    }

    @Override
    @RolesAllowed("ANSWER_QUESTION_MRE")
    public void editApproach(ApproachEntity approach, List<AnswerEntity> answers) throws ApplicationBaseException {
        for(AnswerEntity editedAnswer : answers)
        {
            for(AnswerEntity answerEntity : approach.getAnswers())
            {
                if(editedAnswer.getId() == answerEntity.getId())
                {
                    answerEntity.setContent(editedAnswer.getContent());
                    answerEntity.setDateModification(Calendar.getInstance());
                }
            }
        }
        approachEntityFacade.edit(approach);
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public void endApproach(ApproachEntity approach) throws ApplicationBaseException {
        throw new UnsupportedOperationException();
    }

    @Override
    @RolesAllowed("LIST_AVAILABLE_EXAMS")
    public List<ExamEntity> findAvailableExams() {
        return examEntityFacade.findByDate(Calendar.getInstance());
    }
}
