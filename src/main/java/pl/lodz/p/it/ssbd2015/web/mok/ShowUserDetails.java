package pl.lodz.p.it.ssbd2015.web.mok;


import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

/**
 * Backing bean dla strony administratora do edycji użytkowników.
 * @author Created by adam on 15.04.15.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "showUserDetailsMOK")
@ViewScoped
public class ShowUserDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private PersonServiceRemote personService;

    private String login;

    private String message;

    private PersonEntity person;

    private transient DataModel<GroupsStubEntity> groupStubs;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws PersonEntityNotFoundException {
        this.login = login;
        person = personService.getPerson(login);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String confirmUser() {
        personService.confirmPerson();

        FacesContext context = FacesContext.getCurrentInstance();
        message = context.getApplication()
                .evaluateExpressionGet(context, "#{i18n['mok.person.confirm_message']}", String.class);

        return "showUser?faces-redirect=true&includeViewParams=true";
    }

    public String toggleGroupActive() {
        personService.toggleGroupActivation(groupStubs.getRowData().getId());

        FacesContext context = FacesContext.getCurrentInstance();
        message = context.getApplication()
                .evaluateExpressionGet(context, "#{i18n['mok.person.toggle_group_active_message']}", String.class);

        return "showUser?faces-redirect=true&includeViewParams=true";
    }

    public String togglePersonActive() {
        personService.togglePersonActivation();

        FacesContext context = FacesContext.getCurrentInstance();
        message = context.getApplication()
                .evaluateExpressionGet(context, "#{i18n['mok.person.toggle_person_active_message']}", String.class);

        return "showUser?faces-redirect=true&includeViewParams=true";
    }

}
