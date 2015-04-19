package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Backing bean dla formularza rejestracji nowego użytkownika.
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "registerUserMOK")
@ViewScoped
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;
    private PersonEntity person;

    @EJB
    private PeopleServiceRemote peopleService;

    @PostConstruct
    private void initialize() {
        person = new PersonEntity();
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String register() {
        peopleService.register(person);
        return "register?faces-redirect=true&includeViewParams=true";
    }
}
