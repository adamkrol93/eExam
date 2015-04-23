package pl.lodz.p.it.ssbd2015.web.mok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * BackingBean dla uzytkownika nie będącego administratorem.
 * @author Adam Król
 */
@ManagedBean(name = "showLoggedUserDetailsMOK")
@ViewScoped
public class ShowLoggedUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(ShowLoggedUserDetails.class);

    @EJB
    private PersonServiceRemote personService;

    private PersonEntity person;

    @PostConstruct
    private void initializeModel() {
        try {
            this.person = personService.getLoggedPerson();
        } catch (PersonEntityNotFoundException ex) {
            logger.error("Encountered exception while initializing the bean.", ex);
        }
    }

    public PersonEntity getPerson() {
        return person;
    }
}
