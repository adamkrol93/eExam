package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.TeacherNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.ApproachNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.ApproachEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mre.managers.AnswersManagerLocal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Klasa do zarządzania podejściami juz rozwiązanymi oraz dostępnymi egzaminami. Klasa nie posiada żadnego pola encyjnego.
 * @author Bartosz Ignaczewski
 * @author Tobiasz Kowalski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mre.services.ApproachesService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class ApproachesService extends BaseStatefulService implements ApproachesServiceRemote {

    @EJB
    private AnswersManagerLocal answersManager;

    @EJB
    private ApproachEntityFacadeLocal approachEntityFacade;

    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;

    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacade;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("LIST_APPROACHES_MRE")
    public List<ApproachEntity> listAllForStudent() throws ApplicationBaseException {
    	throw new UnsupportedOperationException();
    }

    @Override
    @RolesAllowed("LIST_APPROACHES_MRE")
    public List<ApproachEntity> listAllForGuardian() throws ApplicationBaseException {
    	throw new UnsupportedOperationException();
    }

    @Override
    @RolesAllowed("SHOW_APPROACHES_MRE")
    public ApproachEntity findById(long id) throws ApplicationBaseException {
        return approachEntityFacade.findById(id)
                                       .orElseThrow(() -> new ApproachNotFoundException(
                                           "Approach with id = " + id + " does not exists"));
    }

    @Override
    @RolesAllowed("LIST_AVAILABLE_EXAMS")
    public List<ExamEntity> findAvailableExams() throws ApplicationBaseException {

        List<ExamEntity> exams = answersManager.findAvailableExams();
        String login = sessionContext.getCallerPrincipal().getName();
        StudentEntity student= studentEntityFacade.findByLogin(login).orElseThrow(() -> new TeacherNotFoundException("Teacher with login: " + login + " does not exists"));;

        List<ApproachEntity> studentApproaches=student.getEntered();
        List<ExamEntity> allExams = studentApproaches.stream().map(ApproachEntity::getExam).collect(Collectors.toList());
        Set<ExamEntity> uniqueExams = new HashSet<>(allExams);

        for(ExamEntity exam : uniqueExams)
            if(exams.contains(exam) && Collections.frequency(allExams,exam)>=exam.getCountTakeExam())
                    exams.remove(exam);

        return exams;
    }
}
