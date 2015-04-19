package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Backing bean dla strony dostępnej dla wszystkich do edycji własnego profilu.
 * @author Created by Marcin on 2015-04-17.
 */
@ManagedBean(name = "editUserDetailsMOK")
@ViewScoped
public class EditUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditPersonServiceRemote editPersonServiceRemote;

    private String login;

    private String message;

    private PersonEntity person;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws PersonEntityNotFoundException {
        this.login = login;
        person = editPersonServiceRemote.findPersonForEdit(login);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String editPerson() {
        editPersonServiceRemote.editPerson(person);

        FacesContext context = FacesContext.getCurrentInstance();
        message = context.getApplication()
                .evaluateExpressionGet(context, "#{i18n['mok.edit.person_changed_message']}", String.class);

        return "editUser?faces-redirect=true&includeViewParams=true";
    }

}
