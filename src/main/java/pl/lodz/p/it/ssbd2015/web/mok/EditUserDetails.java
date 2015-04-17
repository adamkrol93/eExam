package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Marcin on 2015-04-17.
 */
@ManagedBean(name = "editUserDetailsMOK")
@ViewScoped
public class EditUserDetails {

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

    public PersonEntity getPersonEntity() { return personEntity;}

    public void setPersonEntity(PersonEntity personEntity){
        this.personEntity = personEntity;
    }

    public PersonEntity findPersonForEdit() throws PersonEntityNotFoundException {
        return editPersonServiceRemote.findPersonForEdit(login);
    }
    public void editPerson() throws NoSuchAlgorithmException {
        editPersonServiceRemote.editPerson(personEntity);
    }
}
