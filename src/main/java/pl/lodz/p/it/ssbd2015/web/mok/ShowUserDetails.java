package pl.lodz.p.it.ssbd2015.web.mok;


import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by adam on 15.04.15.
 */
@ManagedBean(name = "showUserDetailsMOK")
@ViewScoped
public class ShowUserDetails {

    private String login;

    @EJB
    private PersonServiceRemote personServiceRemote;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PersonEntity getPerson() throws PersonEntityNotFoundException {
        return personServiceRemote.getPerson(login);
    }

    public void confirmUser()
    {
        personServiceRemote.confirmPerson();
    }
}
