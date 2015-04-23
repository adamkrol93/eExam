package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla formularza rejestracji nowego użytkownika.
 *
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "registerUserMOK")
@ViewScoped
public class Register extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private PeopleServiceRemote peopleService;

    private String message;

    private PersonEntity person;

    @PostConstruct
    private void initialize() {
        person = new PersonEntity();
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String register() {
        peopleService.register(person);

        setContext(Register.class, bean -> bean.message = "mok.register.registered_message");
        return "register?faces-redirect=true&includeViewParams=true";
    }
}
