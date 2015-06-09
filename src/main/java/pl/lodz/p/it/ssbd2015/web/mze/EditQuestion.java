package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.mze.services.EditQuestionServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Backing bean dla formularza edycji pytania
 * @author Bartosz Ignaczewski
 */
@ManagedBean(name = "editQuestionMZE")
@ViewScoped
public class EditQuestion extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditQuestionServiceRemote editQuestionService;

    private long id;

    private QuestionEntity question;

    private String message;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            question = editQuestionService.findById(id);
            setContext(EditQuestion.class, bean -> bean.id = id);
        });
    }

    /**
     * Metoda odpowiada za zapisanie/edycje pytania
     * @return strona na którą przekierowywuje po skończonej operacji wraz z odpowiednia wiadomością
     */
    public String saveQuestion() {
        return expectApplicationException(() -> {
            long newId = editQuestionService.editQuestion(question);
            setContext(EditQuestion.class, bean -> {
                bean.id = newId;
                bean.message = "mze.edit_question.edited_message";
            });
            return "editQuestion?faces-redirect=true&includeViewParams=true";
        });
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public String getMessage() {
        return message;
    }
}
