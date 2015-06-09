package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.moe.services.GuardianStudentServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.LinkedList;
import java.util.List;

/**
 * Backing Bean do obsługi przypisywania opiekuna do ucznia.
 * @author Adam Król
 */

@ManagedBean(name = "studetnGuardianConnectionMOE")
@ViewScoped
public class StudentGuardianConnection extends BaseContextBean {


    private static final long serialVersionUID = 1L;

    @EJB
    private GuardianStudentServiceRemote guardianStudentService;

    private List<GuardianEntity> guardianEntityList;

    private List<StudentEntity> studentEntityList;

    private int guardian;

    private int student = 0;

    private List<SelectItem> guardians;

    private List<SelectItem> students;

    private String message;

    @PostConstruct
    private void init()
    {
        this.guardianEntityList = guardianStudentService.findAllGuardians();
        this.studentEntityList = guardianStudentService.findAllStudents();
    }

    @Override
    protected void doInContext() {
        resetContext();
    }

    /**
     * Metoda zamienia listę Opiekunów na odpowiednie obiekty do wyświetlanie użytkownikowi, bez zdradzania tajnych informacji biznsowych
     * @return List obiektów SelectItem, które jako value maja index obiektu w liscie a label ustawiony jako konkatenacja imienia i nazwiska
     */
    public List<SelectItem> getGuardians() {
        if(guardians == null)
        {
            guardians = new LinkedList<>();
            this.guardianEntityList.parallelStream().forEach((guard) ->
                    this.guardians.add(new SelectItem(guardianEntityList.indexOf(guard),guard.getPersonName() + " " + guard.getLastName()))
            );
        }
        return guardians;
    }

    /**
     * Metoda zamienia listę Opiekunów na odpowiednie obiekty do wyświetlanie użytkownikowi, bez zdradzania tajnych informacji biznsowych
     * @return List obiektów SelectItem, które jako value maja index obiektu w liscie a label ustawiony jako konkatenacja imienia i nazwiska
     */
    public List<SelectItem> getStudents() {
        if(students == null)
        {
            students = new LinkedList<>();
            this.studentEntityList.parallelStream().forEach((stud) ->
                            this.students.add(new SelectItem(studentEntityList.indexOf(stud),stud.getPersonName() + " " + stud.getLastName()))
            );
        }
        return students;
    }

    /**
     * Metoda zapisuje w bazie powiązanie, wykorzystując dane zapisane w Beanie.
     * @return stronę z przekierowaniem po udanej transkacji.
     */
    public String doConnection()
    {
        return expectApplicationException(() -> {
            this.guardianStudentService.connect(guardianEntityList.get(guardian).getId(),studentEntityList.get(student).getId());
            setContext(StudentGuardianConnection.class, bean -> bean.message = "moe.student_guardian.done_message");
            return "studentGuardianConnecting.xhtml?faces-redirect=true&includeViewParams=true";
        });
    }

    public int getGuardian() {
        return guardian;
    }

    public void setGuardian(int guardian) {
        this.guardian = guardian;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
