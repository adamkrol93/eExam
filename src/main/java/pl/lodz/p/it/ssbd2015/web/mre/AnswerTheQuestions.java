package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.AnswerServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla strony na której uczeń poznaje treść pytań i udziela odpowiedzi.
 *
 * @author Marcin Kabza
 */
@ManagedBean(name = "answerTheQuestionsMRE")
@ViewScoped
public class AnswerTheQuestions extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private AnswerServiceRemote answerService;

    private long id;

    private ApproachEntity approach;

    private String message;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = answerService.findById(id);
        });
    }

    public String editApproach() {
        return expectApplicationException(() -> {
            answerService.editApproach(approach.getAnswers());
            setContext(AnswerTheQuestions.class, bean -> {
                bean.id = id;
                bean.message = "mre.answer_the_questions.edit_success";
            });
            return String.format("answerTheQuestions?uuid=%s&faces-redirect=true", getUuid());
        });
    }

    public String endApproach() {
        return expectApplicationException(() -> {
            answerService.endApproach();
            setContext(ListApproachesStudent.class, bean -> bean.setMessage("mre.answer_the_questions.end_success"));
            return String.format("listApproaches?uuid=%s&faces-redirect=true", getUuid());
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
