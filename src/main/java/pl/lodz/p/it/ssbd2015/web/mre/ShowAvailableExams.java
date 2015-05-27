package pl.lodz.p.it.ssbd2015.web.mre;


import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesService;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobiasz Kowalski on 22.05.15.
 */
@ManagedBean(name = "showAvailableExamsMRE")
@ViewScoped
public class ShowAvailableExams extends BaseContextBean{

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private List<ExamEntity> examEntities;

    private List<ApproachEntity> approachEntities;

    @PostConstruct
    private void initializeModel() {
        try {
            examEntities= approachesService.findAvailableExams();
            approachEntities= approachesService.listAllForStudent();
        } catch (ApplicationBaseException e) {
            e.printStackTrace();
        }
    }

    public List<ExamEntity> getExamEntities(){return examEntities;}


    public long getApprochesCount(ExamEntity exam){
        return new ArrayList<>(approachEntities).stream().filter(a -> a.getExam().equals(exam)).count();
    }
}
