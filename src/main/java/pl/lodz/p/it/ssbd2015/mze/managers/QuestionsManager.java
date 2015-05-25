package pl.lodz.p.it.ssbd2015.mze.managers;

import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExaminerNotFoundException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExaminerEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacadeLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;

/**
 * Implementacja interfejsu {@link QuestionsManagerLocal}. Pozwala na edycje i dodawanie pytań do systemu.
 * @author Bartosz Ignaczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.QuestionManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class QuestionsManager implements QuestionsManagerLocal {

    @EJB
    private QuestionEntityFacadeLocal questionEntityFacade;

    @EJB
    private ExaminerEntityFacadeLocal examinerEntityFacade;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("CREATE_QUESTION_MZE")
    public void createQuestion(QuestionEntity question) throws ApplicationBaseException {
    	String examinerLogin = sessionContext.getCallerPrincipal().getName();
    	ExaminerEntity examinerEntity = examinerEntityFacade.findByLogin(examinerLogin)
    			.orElseThrow(() -> new ExaminerNotFoundException("Examiner with login: " + examinerLogin + " does not exists"));
    	question.setCreator(examinerEntity);

    	questionEntityFacade.create(question);
    }

    @Override
    @RolesAllowed("EDIT_QUESTION_MZE")
    public void editQuestion(QuestionEntity question, QuestionEntity newQuestion) throws ApplicationBaseException {

        String examinerLogin = sessionContext.getCallerPrincipal().getName();
        ExaminerEntity examinerEntity = examinerEntityFacade.findByLogin(examinerLogin)
                .orElseThrow(() -> new ExaminerNotFoundException("Examiner with login: " + examinerLogin + " does not exists"));

        if (!question.getAnswers().isEmpty()) {
            question = new QuestionEntity();
        }

        question.setContent(newQuestion.getContent());
        question.setSampleAnswer(newQuestion.getSampleAnswer());

        if (!question.getAnswers().isEmpty()) {
            question.setCreator(examinerEntity);
            questionEntityFacade.create(question);
        } else {
            question.setModifier(examinerEntity);
            question.setAnswers(newQuestion.getAnswers());
            question.setExams(newQuestion.getExams());
            questionEntityFacade.edit(question);
        }
    }
}