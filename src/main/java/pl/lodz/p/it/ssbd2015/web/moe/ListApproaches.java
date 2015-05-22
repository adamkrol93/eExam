
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
     * Metoda sprawdzająca czy dane podejście zostało już sprawdzone czy też nie.
     * @param approachId id podejścia które ma zostać sprawdzone
     * @param examId id egzaminu powiązanego z podejściem
     * @return jeżeli podejście było już sprawdzone to: moe.list.approaches.to_evaluate.no jeżeli nie to: moe.list.approaches.to_evaluate.no
     */
    public String checkApproachIsToEvaluate(int approachId, int examId) {
        for (ExamEntity examEntity : examEntityList) {
            if (examEntity.getId() == examId) {
                for (ApproachEntity approachEntity : examEntity.getApproaches()) {
                    if (approachEntity.getId() == approachId) {
                        for (AnswerEntity answerEntity : approachEntity.getAnswers()) {
                            if (answerEntity.getTeacher() == null) {
                                return "moe.list.approaches.to_evaluate.yes";
                            }
                        }
                    }
                }
            }
        }
        return "moe.list.approaches.to_evaluate.no";
    }

    public List<ExamEntity> getExamEntityList()
    {
        return this.examEntityList;
    }
}
