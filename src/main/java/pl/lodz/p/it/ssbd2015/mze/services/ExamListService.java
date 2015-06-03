package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mze.managers.ExamsManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Klasa pozwalająca na wyświetlanie i klonowanie egzaminów.
 * Klasa posiada pole exams typu listy encji {@link ExamEntity}
 * @author Bartosz Ignaczewski
 * @author Adam Król
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mze.services.ExamListService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ExamListService extends BaseStatefulService implements ExamListServiceRemote {

    @EJB
    private ExamEntityFacadeLocal examEntityFacade;

    @EJB
    private ExamsManagerLocal examsManager;

    private List<ExamEntity> exams;

    @Override
    @RolesAllowed("LIST_EXAMS_MZE")
    public List<ExamEntity> findAll() {
        this.exams = examEntityFacade.findAll();
    	return this.exams;
    }

    @Override
    @RolesAllowed("CLONE_EXAM_MZE")
    public void cloneExam(long examId) throws ApplicationBaseException {
        for (ExamEntity e : exams) {
            if (e.getId() == examId) {
                examsManager.cloneExam(e);
                return;
            }
        }
        throw new ExamNotFoundException("Exam with id " + examId + " was not found.");
    }
}
