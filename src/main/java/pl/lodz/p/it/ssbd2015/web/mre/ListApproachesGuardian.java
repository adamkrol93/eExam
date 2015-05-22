package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Backing bean do strony z listą podejść podopiecznych opiekuna.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listApproachesGuardianMRE")
@ViewScoped
public class ListApproachesGuardian extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private List<ApproachEntity> approaches;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approaches = approachesService.listAllForGuardian();
        });
    }

    public List<ApproachEntity> getApproaches() {
        return approaches;
    }
}
