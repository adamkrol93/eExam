package pl.lodz.p.it.ssbd2015.web.mre;


import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Backing bean dla formularza wyświetlającego dostępne egzaminy.
 * Created by Tobiasz Kowalski on 22.05.15.
 */
@ManagedBean(name = "showAvailableExamsMRE")
@ViewScoped
public class ShowAvailableExams extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private List<ExamEntity> examEntities;


    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            examEntities = approachesService.findAvailableExams();
        });
    }

    public List<ExamEntity> getExamEntities(){return examEntities;}

    /**
     * Zlicza liczbę podejść ucznia do egzaminu.
     * @param exam egzamin dla którego mają zostać zliczone egzaminy ucznia.
     * @return zwraca ilośc podejść
     */
    public long getApprochesCount(ExamEntity exam){
        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return new ArrayList<>(exam.getApproaches()).stream().filter(a -> a.getEntrant().getLogin().equals(login)).count();
    }
}
