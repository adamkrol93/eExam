package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ApproachNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Implementacja Endpointu, pozwala na obsługę podejścia (rozpoczęcie, zakończenie edycja).
 * Klasa posiada pole typu {@link ApproachEntity}
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mre.services.AnswerService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class AnswerService extends BaseStatefulService implements AnswerServiceRemote {

    @EJB
    private AnswersManagerLocal answersManager;

    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    private ApproachEntity approach;

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public Long createApproach(String title) throws ApplicationBaseException {
    	throw new UnsupportedOperationException();
    }

    @Override
    @RolesAllowed({"ANSWER_QUESTION_MRE", "END_APPROACH_MRE"})
    public ApproachEntity findById(long approachId) throws ApplicationBaseException {
        this.approach = approachEntityFacade.findById(approachId)
                .orElseThrow(() -> new ApproachNotFoundException(
                        "Approach with id = " + approachId + " does not exists"));
        this.approach.getAnswers().isEmpty();
        return this.approach;
    }

    @Override
    @RolesAllowed("ANSWER_QUESTION_MRE")
    public void editApproach(List<AnswerEntity> answers) throws ApplicationBaseException {
    	answersManager.editApproach(this.approach, answers);
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public void endApproach() throws ApplicationBaseException {
    	throw new UnsupportedOperationException();
    }
}