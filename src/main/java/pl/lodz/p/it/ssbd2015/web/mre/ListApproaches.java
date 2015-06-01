package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Calendar;
import java.util.List;

/**
 * Backing bean do strony z listą podejść studenta.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public abstract class ListApproaches extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    protected ApproachesServiceRemote approachesService;

    private transient DataModel<ApproachEntity> approaches;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approaches = new ListDataModel<>(listAll());
        });
    }

    protected abstract List<ApproachEntity> listAll() throws ApplicationBaseException;

    public DataModel<ApproachEntity> getApproaches() {
        return approaches;
    }

    public Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    public String showDetails() {
        long approachId = approaches.getRowData().getId();
        setContext(ShowApproachDetails.class, bean -> bean.setId(approachId));
        return String.format("showApproach?uuid=%s&faces-redirect=true", getUuid());
    }

}