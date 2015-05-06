package pl.lodz.p.it.ssbd2015.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;
import java.util.Calendar;
import java.util.ResourceBundle;


/**
 * Bean zapewnia obsługę poprawnego zalogowania się użytkownika oraz sprawdzenia jego roli
 *
 * @author Tobiasz Kowalski
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @EJB
    private PersonServiceRemote personService;

    @EJB
    private PeopleServiceRemote peopleService;

    private PersonEntity loggedUser;

    /**
     * Metoda zapisujaca czas, ip oraz login do bazy zaraz po zalogowaniu się użytkownika
     *
     * @return metoda zwraca wartość true gdy użytkownik zalogował się poprawnie do systemu
     * @throws ApplicationBaseException
     */
    public boolean isPersonLogged() throws ApplicationBaseException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        Calendar time = Calendar.getInstance();

        logger.info("Uruchomienie funkcji logowania");
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null && loggedUser == null) {
            loggedUser = personService.getLoggedPerson();
            logger.info("Ktoś się właśnie zalogował");
            peopleService.correctLogin(loggedUser.getLogin(), request.getRemoteAddr(), time);
            return true;
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null && loggedUser != null) {
            loggedUser = null;
            return false;
        }
        return false;
    }


    /**
     * Metoda sprawdzająca czy uzytkownik znajduje się w roli.
     * Metoda korzysta z ResourceBundle o nazwie roles.properties. Role tam zawarte musza odpowiedać poszczególnym menu.
     * @param role klucz z pliku roles.properties roli którą chcemy sprawdzić.
     * @return true - jeżeli użytkownik jest w danej roli, false - jeżeli użytkownik nie znajduje się w roli
     */
    private boolean isInRole(String role){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(ResourceBundle.getBundle("roles").getString(role));
    }

    /**
     * Metoda sprawdza czy zalogowany użytkownik jest administratorem.
     * Więcej informacji patrz {@link LoginBean#isInRole(String)}
     * @return true - jeżeli użytkownik jest w Administratorem, false - jeżeli użytkownik nie jest Administratorem
     */
    public boolean isAdministrator() {
        return isInRole("admin");
    }

    /**
     * Metoda sprawdza czy zalogowany użytkownik jest studentem.
     * Więcej informacji patrz {@link LoginBean#isInRole(String)}
     * @return true - jeżeli użytkownik jest w studentem, false - jeżeli użytkownik nie jest studentem
     */
    public boolean isStudent() {
        return isInRole("student");
    }

    /**
     * Metoda sprawdza czy zalogowany użytkownik jest nauczycielem.
     * Więcej informacji patrz {@link LoginBean#isInRole(String)}
     * @return true - jeżeli użytkownik jest w nauczycielem, false - jeżeli użytkownik nie jest nauczycielem
     */
    public boolean isTeacher() {
        return isInRole("teacher");
    }

    /**
     * Metoda sprawdza czy zalogowany użytkownik jest opiekunem.
     * Więcej informacji patrz {@link LoginBean#isInRole(String)}
     * @return true - jeżeli użytkownik jest w opiekunem, false - jeżeli użytkownik nie jest opiekunem
     */
    public boolean isGuardian() {
        return isInRole("guardian");
    }

    /**
     * Metoda sprawdza czy zalogowany użytkownik jest egzaminatorem.
     * Więcej informacji patrz {@link LoginBean#isInRole(String)}
     * @return true - jeżeli użytkownik jest w egzaminatorem, false - jeżeli użytkownik nie jest egzaminatorem
     */
    public boolean isExaminer() {
        return isInRole("examiner");
    }

    /**
     * Metoda wyciąga login aktualnego użytkownika.
     * @return
     */
    public String getLogin() {
        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return p.getName();
    }
}
