package pl.lodz.p.it.ssbd2015.web.mok;


import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.mail.MessagingException;

/**
 * Backing bean dla strony administratora do edycji użytkowników.
 * @author Created by adam on 15.04.15.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "showUserDetailsMOK")
@ViewScoped
public class ShowUserDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private PersonServiceRemote personService;

    private String login;

    private String message;

    private PersonEntity person;

    private transient DataModel<GroupsStubEntity> groupStubs;

    @Override
    protected void doInContext() {
        try {
            person = personService.getPerson(login);
        } catch (ApplicationBaseException ex) {
            logger.error("Encountered exception while initializing the bean.", ex);
        }

        resetContext();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public DataModel<GroupsStubEntity> getGroupStubs() {
        if (groupStubs == null) {
            groupStubs = new ListDataModel<>(person.getGroupStubs());
        }
        return groupStubs;
    }

    public String confirmUser() throws ApplicationBaseException {
        personService.confirmPerson();

        setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.confirm_message");
        return "showUser?faces-redirect=true&includeViewParams=true";
    }

    public String toggleGroupActive() throws ApplicationBaseException{
        personService.toggleGroupActivation(groupStubs.getRowData().getId());

        setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.toggle_group_active_message");
        return "showUser?faces-redirect=true&includeViewParams=true";
    }

    public String togglePersonActive() throws ApplicationBaseException {
        personService.togglePersonActivation();

        setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.toggle_person_active_message");
        return "showUser?faces-redirect=true&includeViewParams=true";
    }

}
