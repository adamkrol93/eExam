package pl.lodz.p.it.ssbd2015.web.mre;


import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesService;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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


    @PostConstruct
    private void initializeModel() {
        try {
            examEntities= approachesService.findAvailableExams();
        } catch (ApplicationBaseException e) {
            e.printStackTrace();
        }
    }

    public List<ExamEntity> getExamEntities(){return examEntities;}


    public long getApprochesCount(ExamEntity exam){

        //String login = sessionContext.getCallerPrincipal().getName();



        String login = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return new ArrayList<>(exam.getApproaches()).stream().filter(a -> a.getEntrant().getLogin().equals(login)).count();

        //return new ArrayList<>(approachEntities).stream().filter(a -> a.getExam().equals(exam)).count();
    }
}
