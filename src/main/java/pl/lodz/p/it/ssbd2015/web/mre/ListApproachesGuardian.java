package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Backing bean do strony z listą podejść podopiecznych opiekuna.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listApproachesGuardianMRE")
@ViewScoped
public class ListApproachesGuardian extends ListApproaches {

    private static final long serialVersionUID = 1L;

    /**
     * Sporządza listę podejść dla podopiecznych obecnie zalogowanego opiekuna korzystając z ziarna EJB.
     * @return Sporządzona lista podejść.
     * @throws ApplicationBaseException Jeżeli nie uda się znaleźć opiekuna.
     */
    protected List<ApproachEntity> listAll() throws ApplicationBaseException {
        return approachesService.listAllForGuardian();
    }

}
