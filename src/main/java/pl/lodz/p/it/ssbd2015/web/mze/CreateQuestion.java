package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamsServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla formularza tworzącego pytanie
 * @author Adam Król
 */
@ManagedBean(name = "createQuestionMZE")
@ViewScoped
public class CreateQuestion extends BaseContextBean{

    private static final long serialVersionUID = 1L;

    @EJB
    private ExamsServiceRemote examsService;

    private QuestionEntity question;

    private String message;

    @PostConstruct
    private void init()
    {
        this.question = new QuestionEntity();
    }

    public String createQuestion()
    {
        return expectApplicationException(() ->{
            this.examsService.create(question);
            setContext(CreateQuestion.class, bean -> bean.message = "mze.create_question.created_message");
            return "createQuestion?faces-redirect=true&includeViewParams=true";
        });
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }
}
