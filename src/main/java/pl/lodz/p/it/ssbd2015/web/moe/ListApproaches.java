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
        for (AnswerEntity answerEntity : approach.getAnswers()) {
            if (answerEntity.getTeacher() == null) {
                return "moe.approach_list.approaches.to_evaluate.yes";
            }
        }
        return "moe.approach_list.approaches.to_evaluate.no";
    }

    public List<ExamEntity> getExamEntityList()
    {
        return this.examEntityList;
    }
}
