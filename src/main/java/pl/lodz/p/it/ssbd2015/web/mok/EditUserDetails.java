package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.services.EditPersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla strony dostępnej dla wszystkich do edycji własnego profilu.
 * @author Created by Marcin on 2015-04-17.
 */
@ManagedBean(name = "editUserDetailsMOK")
@ViewScoped
public class EditUserDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditPersonServiceRemote editPersonServiceRemote;

    private String login;

    private String message;

    private PersonEntity person;

    @Override
    protected void doInContext() {
        try {
            person = editPersonServiceRemote.findPersonForEdit(login);
        } catch (ApplicationBaseException ex) {
            logger.error("Encountered exception while initializing the bean.", ex);
        }

        resetContext();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws ApplicationBaseException {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public String editPerson() {
        editPersonServiceRemote.editPerson(person);

        setContext(EditUserDetails.class, bean -> bean.message = "mok.edit.person_changed_message");
        return "editUser?faces-redirect=true&includeViewParams=true";
    }

}
