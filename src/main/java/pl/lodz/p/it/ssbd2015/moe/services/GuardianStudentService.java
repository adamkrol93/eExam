package pl.lodz.p.it.ssbd2015.moe.services;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.GuardianNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.StudentNotFoundException;
import pl.lodz.p.it.ssbd2015.moe.facades.GuardianEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.moe.managers.ApproachesManagerLocal;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Metoda implementująca {@link GuardianStudentServiceRemote} pozwala na wykonanie metod związanych z łączeniem opiekunów i studentów.
 * Klasa zawiera pole z listami studentów oraz opiekunów.
 * @author Bartosz Ignaczewski
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.moe.services.GuardianStudentService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(LoggingInterceptor.class)
public class GuardianStudentService extends BaseStatefulService implements GuardianStudentServiceRemote {

    @EJB
    private ApproachesManagerLocal approachesManager;

    @EJB
    private GuardianEntityFacadeLocal guardianEntityFacade;

    @EJB
    private StudentEntityFacadeLocal studentEntityFacade;

    private List<GuardianEntity> guardians;

    private List<StudentEntity> students;

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public List<GuardianEntity> findAllGuardians() {
        this.guardians = guardianEntityFacade.findAll()
                            .stream()
                            .filter((guardian) -> guardian.isActive() && guardian.isConfirm() && guardian.isGroupActive())
                            .collect(Collectors.toList());
        return this.guardians;
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public List<StudentEntity> findAllStudents() {
        this.students = studentEntityFacade.findAll()
                            .stream()
                            .filter((student) -> student.isActive() && student.isConfirm() && student.isGroupActive())
                .collect(Collectors.toList());
        return this.students;
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public void connect(long guardianId, long studentId) throws ApplicationBaseException {
        StudentEntity studentEntity = this.students.stream()
                .filter(studentEntity1 -> studentEntity1.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + studentId + " was not found."));
        GuardianEntity guardianEntity = this.guardians.stream()
                .filter(guardianEntity1 -> guardianEntity1.getId() == guardianId)
                .findFirst()
                .orElseThrow(() -> new GuardianNotFoundException("Guardian with id " + guardianId + " was not found."));
        approachesManager.connect(guardianEntity,studentEntity);
    }
}
