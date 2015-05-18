package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.moe.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla strony nauczyciela, wyświetlającej szczegóły podejścia
 *
 * @author Bartosz Ignaczewski
 */
@ManagedBean(name = "showApproachDetailsMOE")
@ViewScoped
public class ShowApproachDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private ApproachEntity approach;

    private long id;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = approachesService.findById(id);
            resetContext();
        });
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApproachEntity getApproach() {
        return approach;
    }

    public void setApproach(ApproachEntity approach) {
        this.approach = approach;
    }

}
