package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.moe.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * @author Marcin Kabza
 */
@ManagedBean(name = "listApproachesMOE")
@ViewScoped
public class ListApproaches extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private List<ExamEntity> examEntityList;

    private transient DataModel<ApproachEntity> approaches;

    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            this.examEntityList = approachesService.findAllByLoggedTeacher();
        });
    }

    /**
     * Metoda sprawdzająca czy wszystkie odpowiedzi w danym podejści zostały już sprawdzone czy też nie.
     * @param approach Podejście, które ma zostać sprawdzone
     * @return Zwraca kod wiadomości do wyświetlenia. Jeżeli podejście było już sprawdzone to: moe.list.approaches.to_evaluate.no jeżeli nie to: moe.list.approaches.to_evaluate.no
     */
    public String checkApproachIsToEvaluate(ApproachEntity approach) {
        if(approaches.getRowData().isDisqualification()){
            return "moe.approach_list.approaches.to_evaluate.disqualified";
        }

        for (AnswerEntity answerEntity : approach.getAnswers()) {
            if (answerEntity.getTeacher() == null) {
                return "moe.approach_list.approaches.to_evaluate.yes";
            }
        }
        return "moe.approach_list.approaches.to_evaluate.no";

    }

    /**
     * Metoda korzysta z dobrodziejstw contextMapy i ustawia
     * odpowiednie identyfikator do przejścia do szczegółów podejścia
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String gotoDetails() {
        long approachId = approaches.getRowData().getId();
        setContext(ShowApproachDetails.class, bean -> bean.setId(approachId));
        return String.format("showApproachDetails?uuid=%s&faces-redirect=true", getUuid());
    }

    public String rateApproach() {
        long approachId = approaches.getRowData().getId();
        setContext(RateApproach.class, bean -> bean.setId(approachId));
        return String.format("rateApproach?uuid=%s&faces-redirect=true", getUuid());
    }

    public DataModel<ApproachEntity> getApproaches(ExamEntity exam) {
        for (ExamEntity examEntity : examEntityList) {
            if (examEntity.getId() == exam.getId()) {
                this.approaches = new ListDataModel<>(examEntity.getApproaches());
            }
        }
        return approaches;
    }

    public List<ExamEntity> getExamEntityList()
    {
        return this.examEntityList;
    }
}
