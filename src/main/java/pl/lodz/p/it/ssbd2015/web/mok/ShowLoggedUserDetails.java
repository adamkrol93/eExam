package pl.lodz.p.it.ssbd2015.web.mok;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.services.PersonServiceRemote;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * BackingBean dla uzytkownika nie będącego administratorem.
 * @author Adam Król
 */
@ManagedBean(name = "showLoggedUserDetailsMOK")
@ViewScoped
public class ShowLoggedUserDetails {

    @EJB
    private PersonServiceRemote personService;

    private PersonEntity person;

    public PersonEntity getPerson() throws PersonEntityNotFoundException {
        if (person == null) {
            this.person = personService.getLoggedPerson();
        }
        return person;
    }
}
