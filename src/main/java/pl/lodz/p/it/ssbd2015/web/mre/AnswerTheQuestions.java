package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.AnswerServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla strony na której uczeń poznaje treść pytań i udziela odpowiedzi.
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

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = answerService.findById(id);
        });
    }

    public String editApproach(){
        expectApplicationException(() -> {
            answerService.editApproach(approach.getAnswers());
        });
        //TODO: Należy dorobić przekazanie odpowiedniego parametru do strony endApproach
        //setContext(EndApproach.class, bean -> bean = );
        return "endApproach?faces-redirect=true";
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
}
