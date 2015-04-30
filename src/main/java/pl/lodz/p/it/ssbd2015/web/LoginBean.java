package pl.lodz.p.it.ssbd2015.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HEAD;
import java.io.Serializable;
import java.util.Calendar;


/**
 * Bean zapewnia obsługę poprawnego zalogowania się użytkownika
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
     * @throws PersonEntityNotFoundException
     */
    public boolean isPersonLogged() throws PersonEntityNotFoundException {
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

    public boolean isAdministrator() {
        try {
            return peopleService.isAdministrator();
        } catch (PersonEntityNotFoundException e) {
            logger.info("No user is logged in.", e);
            return false;
        }
    }

    public boolean isStudent() {
        try {
            return peopleService.isStudent();
        } catch (PersonEntityNotFoundException e) {
            logger.info("No user is logged in.", e);
            return false;
        }
    }

    public boolean isTeacher() {
        try {
            return peopleService.isTeacher();
        } catch (PersonEntityNotFoundException e) {
            logger.info("No user is logged in.", e);
            return false;
        }
    }

    public boolean isGuardian() {
        try {
            return peopleService.isGuardian();
        } catch (PersonEntityNotFoundException e) {
            logger.info("No user is logged in.", e);
            return false;
        }
    }

    public boolean isExaminer() {
        try {
            return peopleService.isExaminer();
        } catch (PersonEntityNotFoundException e) {
            logger.info("No user is logged in.", e);
            return false;
        }
    }

}
