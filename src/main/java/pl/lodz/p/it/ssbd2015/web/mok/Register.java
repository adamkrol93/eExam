package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.interceptors.TryCatchInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.interceptor.Interceptors;
import javax.mail.MessagingException;

/**
 * Backing bean dla formularza rejestracji nowego użytkownika.
 *
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "registerUserMOK")
@ViewScoped
@Interceptors({TryCatchInterceptor.class})
public class Register extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private PeopleServiceRemote peopleService;

    private String message;

    private PersonEntity person;

    @Override
    protected void doInContext() {
        resetContext();
    }

    @PostConstruct
    private void initialize() {
        person = new PersonEntity();
    }

    public String getMessage() {
        return message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String register() throws ApplicationBaseException {

        peopleService.register(person);

        setContext(Register.class, bean -> bean.message = "mok.register.registered_message");
        return "register?faces-redirect=true&includeViewParams=true";
    }
}
