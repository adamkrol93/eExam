package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Backing bean dla strony szczegółów podejścia, zarówno dla ucznia jak i opiekuna.
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "showApproachDetailsMRE")
@ViewScoped
public class ShowApproachDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private ApproachEntity approach;

    private long id;

    private final String[] styles = {"danger", "warning", "info", "success"};

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = approachesService.findById(id);
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

    public long getPoints() {
        return new ArrayList<>(approach.getAnswers()).stream().map(AnswerEntity::getGrade).reduce(0, (a, b) -> a + b);
    }

    public String getResultStyle() {
        int result = (int) (3.2 * getPoints() / getMaxPoints());
        return styles[result];
    }

    public long getMaxPoints() {
        return approach.getAnswers().size() * 2;
    }

    public List<Integer> repeat(int times) {
        return IntStream.range(0, times).boxed().collect(Collectors.toList());
    }
}
