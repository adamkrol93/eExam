package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.exceptions.LoginNotUniqueException;
import pl.lodz.p.it.ssbd2015.exceptions.PasswordTooShortException;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

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

    /**
     * Metoda akcji wykonywanej podczas rejestracji nowego użytkownika
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String register() {
        return expectApplicationException(() -> {
            try {
                peopleService.register(person);
            } catch (LoginNotUniqueException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "register-form:login");
                return null;
            } catch (PasswordTooShortException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "register-form:password");
                return null;
            }

            setContext(Register.class, bean -> bean.message = "mok.register.registered_message");
            return "register?faces-redirect=true&includeViewParams=true";
        });
    }
}
