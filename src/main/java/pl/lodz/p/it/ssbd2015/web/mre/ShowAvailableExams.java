package pl.lodz.p.it.ssbd2015.web.mre;


import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by Tobiasz Kowalski on 22.05.15.
 */
@ManagedBean(name = "showAvailableExamsMRE")
@RequestScoped
public class ShowAvailableExams extends BaseContextBean{

    @EJB
    private ApproachesServiceRemote approachesService;

    private List<ExamEntity> examEntities;


    @PostConstruct
    private void initializeModel() {
        try {
            examEntities= approachesService.findAvailableExams();
        } catch (ApplicationBaseException e) {
            e.printStackTrace();
        }
    }

    public List<ExamEntity> getExamEntities(){return examEntities;}
}
