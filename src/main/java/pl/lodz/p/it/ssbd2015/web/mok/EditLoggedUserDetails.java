package pl.lodz.p.it.ssbd2015.web.mok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.interceptors.TryCatchInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.interceptor.Interceptors;
import java.io.Serializable;

/**
 * BackingBean dla uzytkownika nie będącego administratorem.
 * @author Marcin Kabza
 */
@ManagedBean(name = "editLoggedUserDetailsMOK")
@ViewScoped
@Interceptors({TryCatchInterceptor.class})
public class EditLoggedUserDetails extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(EditLoggedUserDetails.class);

    @EJB
    private EditPersonServiceRemote editPersonService;
    @EJB
    private PersonServiceRemote personService;

    private PersonEntity person;
    private String message;

    @PostConstruct
    private void initializeModel() {
        try {
            this.person = editPersonService.findLoggedPersonForEdit();
        } catch (ApplicationBaseException ex) {
            logger.error("Encountered exception while initializing the bean.", ex);
        }
        resetContext();
    }
    public String getMessage() {
        return message;
    }
    public PersonEntity getPerson() {
        return person;
    }
    public String editPerson() throws ApplicationBaseException {
        editPersonService.editPerson(person);

        setContext(EditLoggedUserDetails.class, bean -> bean.message = "mok.edit.person_changed_message");
        return "editUser?faces-redirect=true&includeViewParams=true";
    }
}
