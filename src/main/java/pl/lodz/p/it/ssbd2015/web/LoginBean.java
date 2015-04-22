package pl.lodz.p.it.ssbd2015.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;


/**
 * Bean zapewnia obsługę poprawnego zalogowania się użytkownika
 * @author Tobiasz Kowalski
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private PersonEntity loggernUser;


    @EJB
    private PersonServiceRemote personService;

    /**
     *
     * @return metoda zwraca wartość true gdy użytkownik zalogował się poprawnie do systemu
     * @throws PersonEntityNotFoundException
     */
    public boolean isPersonLogged() throws PersonEntityNotFoundException {
        logger.info("Uruchomienie funkcji logowania");
        if(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null && loggernUser == null){

            loggernUser = personService.getPerson(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
            logger.info("Ktoś się właśnie zalogował");
            return true;
        }

        if(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() == null && loggernUser != null){
            loggernUser =null;
            return false;
        }

        return false;
    }

}
