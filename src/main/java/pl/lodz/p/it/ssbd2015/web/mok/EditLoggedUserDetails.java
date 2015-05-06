package pl.lodz.p.it.ssbd2015.web.mok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.PasswordTooShortException;
import pl.lodz.p.it.ssbd2015.exceptions.PersonPasswordNotUniqueException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * BackingBean dla uzytkownika nie będącego administratorem.
 * @author Marcin Kabza
 */
@ManagedBean(name = "editLoggedUserDetailsMOK")
@ViewScoped
public class EditLoggedUserDetails extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(EditLoggedUserDetails.class);

    @EJB
    private EditPersonServiceRemote editPersonService;

    private PersonEntity person;
    private String message;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> this.person = editPersonService.findLoggedPersonForEdit());
        resetContext();
    }

    public String getMessage() {
        return message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String editPerson() {
        return expectApplicationException(() -> {
            try {
                editPersonService.editPerson(person);
            } catch (PersonPasswordNotUniqueException | PasswordTooShortException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "editForm:password");
                return null;
            }

            setContext(EditLoggedUserDetails.class, bean -> bean.message = "mok.edit.person_changed_message");
            return "editUser?faces-redirect=true&includeViewParams=true";
        });
    }
}
