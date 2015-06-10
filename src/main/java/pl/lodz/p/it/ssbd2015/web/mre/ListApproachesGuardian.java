package pl.lodz.p.it.ssbd2015.web.mre;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
     */
    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approachList = approachesService.listAllForGuardian();
        });
    }
}
