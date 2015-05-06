package pl.lodz.p.it.ssbd2015.web.mok;


import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
        expectApplicationException(() -> {
            person = personService.getPerson(login);
        });

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

    /**
     * Metoda akcji wykonywanej podczas potwierdzenia użytkownika
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String confirmUser() {
        return expectApplicationException(() -> {
            personService.confirmPerson();

            setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.confirm_message");
            return "showUser?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Metoda akcji wykonywanej podczas dodawania lub usuwania aktywności grupy użytkownika
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String toggleGroupActive() {
        return expectApplicationException(() -> {
            personService.toggleGroupActivation(groupStubs.getRowData().getId());

            setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.toggle_group_active_message");
            return "showUser?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Metoda akcji wykonywanej podczas zmiany statusu aktywności użytkownika ( można użytkownika zablokować)
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String togglePersonActive() {
        return expectApplicationException(() -> {
            personService.togglePersonActivation();

            setContext(ShowUserDetails.class, bean -> bean.message = "mok.person.toggle_person_active_message");
            return "showUser?faces-redirect=true&includeViewParams=true";
        });
    }

}
