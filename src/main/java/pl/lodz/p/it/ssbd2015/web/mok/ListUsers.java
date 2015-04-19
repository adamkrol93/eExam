package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Backing bean do strony z listą wszystkich użytkowników.
 * @author Created by adam on 19.04.15.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listUsersMOK")
@RequestScoped
public class ListUsers {

    @EJB
    private PeopleServiceRemote peopleService;

    private List<PersonEntity> personEntities;

    @PostConstruct
    private void initializeModel() {
        personEntities = peopleService.findAllPeople();
    }

    public List<PersonEntity> getPersonEntities() {
        return personEntities;
    }

}
