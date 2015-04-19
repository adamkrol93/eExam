package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Created by adam on 19.04.15.
 */
@ManagedBean(name = "listUsersMOK")
@ViewScoped
public class ListUsers {

    @EJB
    private PeopleServiceRemote peopleService;

    private DataModel<PersonEntity> personEntities;


    public List<PersonEntity> findAllPersons()
    {
        return peopleService.findAllPeron();
    }

    public DataModel<PersonEntity> getPersonEntities() {
        if(personEntities == null)
        {
            personEntities = new ListDataModel<>(findAllPersons());
        }
        return personEntities;
    }

    public String editPerson()
    {
        return "editUser?faces-redirect=true&login=" + personEntities.getRowData().getLogin();
    }

    public String showPerson()
    {
        return "showUser?faces-redirect=true&login=" + personEntities.getRowData().getLogin();
    }
}
