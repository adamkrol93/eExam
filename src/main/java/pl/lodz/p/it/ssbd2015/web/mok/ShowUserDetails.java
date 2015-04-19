package pl.lodz.p.it.ssbd2015.web.mok;


import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    private PersonEntity person;

    private transient DataModel<GroupsStubEntity> groupStubs;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws PersonEntityNotFoundException {
        this.login = login;
        person = personService.getPerson(login);
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
        return "showUser?faces-redirect=true&includeViewParams=true";
    }

    public String toggleGroupActive() {
        personService.toggleGroupActivation(groupStubs.getRowData().getId());
        return "showUser?faces-redirect=true&includeViewParams=true";
    }

}
