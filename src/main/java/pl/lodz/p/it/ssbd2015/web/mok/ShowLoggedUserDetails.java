package pl.lodz.p.it.ssbd2015.web.mok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

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
public class ShowLoggedUserDetails extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(ShowLoggedUserDetails.class);

    @EJB
    private PersonServiceRemote personService;

    private PersonEntity person;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            this.person = personService.getLoggedPerson();
        });
    }

    public PersonEntity getPerson() {
        return person;
    }
}
