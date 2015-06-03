package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.moe.services.MarkApproachService;
import pl.lodz.p.it.ssbd2015.moe.services.MarkApproachServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Backing bean dla strony nauczyciela, z poziomu któej, może on oceniać odpowiedzi ucznia w danym podejściu, oraz dyskwalifikować podejście
 *
 * @author Piotr Jurewicz
 * @author Tobiasz Kowalski
 */
@ManagedBean(name = "rateApproachMOE")
@ViewScoped
public class RateApproach extends BaseContextBean implements Serializable {

    @EJB
    private MarkApproachServiceRemote markApproachService;

    long id;

    ApproachEntity approach;

    public ApproachEntity getApproach() {
        return approach;
    }

    public void setApproach(ApproachEntity approach) {
        this.approach = approach;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = markApproachService.findById(id);
        });
    }

    /**
     * Metoda utrwala w bazie przypisane odpowiedziom oceny.
     * @return stronę z przekierowaniem po udanej transkacji.
     */
    public String editApproach() {
        return expectApplicationException(() -> {
            markApproachService.mark(approach.getAnswers());

            setContext(ListApproaches.class, (bean -> {
                bean.setMessage("moe.rate_approach.exam_rated");
            }));

            return String.format("listApproaches?uuid=%s&faces-redirect=true", getUuid());
        });
    }

    /**
     * Metoda utrwala w bazie dyskwalifikacje podejścia
     * @return zwraca stronę z przekierowanie po udanej transakcji.
     */
    public String disqualifyApproach() {
        return expectApplicationException(() -> {
            markApproachService.disqualify();

            setContext(ListApproaches.class, (bean -> {
                bean.setMessage("moe.rate_approach.exam_disqualified");
            }));

            return String.format("listApproaches?uuid=%s&faces-redirect=true", getUuid());
        });
    }

    public List<Integer> repeat(int times) {
        return IntStream.range(0, times).boxed().collect(Collectors.toList());
    }

}
