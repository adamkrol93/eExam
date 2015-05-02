package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.services.PeopleServiceRemote;
import pl.lodz.p.it.ssbd2015.web.interceptors.TryCatchInterceptor;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.interceptor.Interceptors;
import java.util.List;

/**
 * Backing bean do strony z listą wszystkich użytkowników.
 * @author Created by adam on 19.04.15.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listUsersMOK")
@RequestScoped
@Interceptors({TryCatchInterceptor.class})
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
