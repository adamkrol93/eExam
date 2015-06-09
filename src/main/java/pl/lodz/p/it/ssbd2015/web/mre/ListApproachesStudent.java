package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Backing bean do strony z listą podejść studenta.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listApproachesStudentMRE")
@ViewScoped
public class ListApproachesStudent extends ListApproaches {

    private static final long serialVersionUID = 1L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Wyszukuje listę podejść obecnie zalogowanego studetna korzystając z ziarna EJB.
     * @return Sporządzona lista podejść.
     * @throws ApplicationBaseException Jeżeli nie uda się znaleźć studenta.
     */
    protected List<ApproachEntity> listAll() throws ApplicationBaseException {
        return approachesService.listAllForStudent();
    }
}
