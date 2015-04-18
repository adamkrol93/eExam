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
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

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

    public void passwordValidator(ComponentSystemEvent event) throws ValidatorException{
        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();
        UIInput passwordField = (UIInput) components.findComponent("editForm:password");
        String password = passwordField.getLocalValue() == null ? ""
                : passwordField.getLocalValue().toString();
        String passwordId = passwordField.getClientId();

        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("editForm:confirm");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (!password.equals(confirmPassword)) {

            FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("i18n.translate",
                    FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("MOK_PASSWORDS_ARE_DIFFERENT"));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(passwordId, msg);
            fc.renderResponse();

        }
    }
}
