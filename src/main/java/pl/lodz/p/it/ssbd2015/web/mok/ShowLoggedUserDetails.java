package pl.lodz.p.it.ssbd2015.web.mok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.interceptors.TryCatchInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.interceptor.Interceptors;
import java.io.Serializable;

/**
 * BackingBean dla uzytkownika nie będącego administratorem.
 * @author Adam Król
 */
@ManagedBean(name = "showLoggedUserDetailsMOK")
@ViewScoped
@Interceptors({TryCatchInterceptor.class})
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
        } catch (ApplicationBaseException ex) {
            logger.error("Encountered exception while initializing the bean.", ex);
        }
    }

    public PersonEntity getPerson() {
        return person;
    }
}
