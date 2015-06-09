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
 * Klasa abstrakcyjna backing beanów do stron z listą podejść studenta.
 * @author Michał Sośnicki
 */
public abstract class ListApproaches extends BaseContextBean {

    @EJB
    protected ApproachesServiceRemote approachesService;

    private List<ApproachEntity> approachList;

    private transient DataModel<ApproachEntity> approaches;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approachList = listAll();
        });
    }

    /**
     * Metoda szablonowa wyszukująca listę podejść do wyświetlenia.
     * @return Sporządzona lista podejść.
     * @throws ApplicationBaseException Sporządzenie listy może zakończyć się błędem.
     */
    protected abstract List<ApproachEntity> listAll() throws ApplicationBaseException;

    public DataModel<ApproachEntity> getApproaches() {
        if (approaches == null) {
            approaches = new ListDataModel<>(approachList);
        }
        return approaches;
    }

    /**
     * Zwraca kalendarz z obecną datą, przydaje się w interfejsie.
     * @return Kalendarz z obecną datą.
     */
    public Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    /**
     * Metoda obsługująca akcję przejścia do szczegółów podejścia. Odczytuje z DataModelu wybraną pozycję
     * i umieszcza jej id w kontekście.
     * @return String z outcome JSF
     */
    public String showDetails() {
        long approachId = approaches.getRowData().getId();
        setContext(ShowApproachDetails.class, bean -> bean.setId(approachId));
        return String.format("showApproach?uuid=%s&faces-redirect=true", getUuid());
    }

}
