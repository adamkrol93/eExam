package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mok.PasswordTooShortException;
import pl.lodz.p.it.ssbd2015.exceptions.mok.PersonPasswordNotUniqueException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Backing bean dla strony dostępnej dla wszystkich do edycji własnego profilu.
 * @author Created by Marcin on 2015-04-17.
 */
@ManagedBean(name = "editUserDetailsMOK")
@ViewScoped
public class EditUserDetails extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditPersonServiceRemote editPersonService;

    private String message;

    private String login;

    private PersonEntity person;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            person = editPersonService.findPersonForEdit(login);
            resetContext();
        });
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    /**
     * Metoda akcji wykonywanej podczas edycji zalogowanego uzytkownika
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String editPerson() {
        return expectApplicationException(() -> {
            try {
                editPersonService.editPerson(person);
            } catch (PersonPasswordNotUniqueException | PasswordTooShortException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "edit-form:password");
                return null;
            }

            setContext(EditUserDetails.class, bean -> bean.message = "mok.edit.person_changed_message");
            return "editUser?faces-redirect=true&includeViewParams=true";
        });
    }
}
