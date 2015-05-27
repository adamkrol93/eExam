package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Backing bean do strony z listą podejść studenta.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listApproachesStudentMRE")
@ViewScoped
public class ListApproachesStudent extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private transient DataModel<ApproachEntity> approaches;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approaches = new ListDataModel<>(approachesService.listAllForStudent());
        });
    }

    public DataModel<ApproachEntity> getApproaches() {
        return approaches;
    }

    public String showDetails() {
        long approachId = approaches.getRowData().getId();
        setContext(ShowApproachDetails.class, bean -> bean.setId(approachId));
        return String.format("showApproach?uuid=%s&faces-redirect=true", getUuid());
    }
}
