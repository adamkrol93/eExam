package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;
import sun.security.validator.ValidatorException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Marcin on 2015-04-17.
 */
@ManagedBean(name = "editUserDetailsMOK")
@ViewScoped
public class EditUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    private String login;
    private PersonEntity personEntity;
    @EJB
    private EditPersonServiceRemote editPersonServiceRemote;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PersonEntity getPersonEntity() throws PersonEntityNotFoundException {
        if(this.personEntity == null) {
            this.personEntity = findPersonForEdit();
        }
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity){

        this.personEntity = personEntity;
    }

    public PersonEntity findPersonForEdit() throws PersonEntityNotFoundException {
        return editPersonServiceRemote.findPersonForEdit(login);
    }
    public void editPerson() throws NoSuchAlgorithmException {
        editPersonServiceRemote.editPerson(personEntity);
    }

    public void passwordValidator(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException{
        UIInput passwordField = (UIInput) context.getViewRoot().findComponent("editForm:password");
        if (passwordField == null)
            throw new IllegalArgumentException(String.format("Unable to find component."));
        String password = (String) passwordField.getValue();
        String confirmPassword = (String) value;
        if (!confirmPassword.equals(password)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", "Passwords do not match!");
            throw new ValidatorException(message);
        }
    }
}
