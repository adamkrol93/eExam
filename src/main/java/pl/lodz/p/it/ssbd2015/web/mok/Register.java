package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Backing bean dla formularza rejestracji nowego użytkownika.
 *
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "registerUserMOK")
@ViewScoped
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;
    private PersonEntity person;

    @EJB
    private PeopleServiceRemote peopleService;

    private String message;

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

        FacesContext context = FacesContext.getCurrentInstance();
        message = context.getApplication()
                         .evaluateExpressionGet(context, "#{i18n['mok.register.registered_message']}", String.class);
        message = MessageFormat.format(message, person.getLogin());
        initialize();

        return "register?faces-redirect=true&includeViewParams=true";
    }
}
