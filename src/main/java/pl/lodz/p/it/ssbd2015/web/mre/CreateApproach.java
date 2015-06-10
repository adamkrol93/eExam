package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.exceptions.moe.ExamNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.StudentNotFoundException;
import pl.lodz.p.it.ssbd2015.exceptions.mre.UnavailableExamException;
import pl.lodz.p.it.ssbd2015.mre.services.AnswerServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Backing bean dla strony, z poziomu której, student rozpoczyna nowe podejście do egzaminu.
 *
 * @author Piotr Jurewicz
 */
@ManagedBean(name = "createApproachMRE")
@ViewScoped
public class CreateApproach extends BaseContextBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private AnswerServiceRemote answerService;

    private String message;

    private String title;

    @Override
    protected void doInContext() {
        resetContext();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metoda akcji wykonywanej podczas tworzenia nowego podejścia
     *
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String createApproach() {
        return expectApplicationException(() -> {
            try {
                long approachId = answerService.createApproach(title);
                setContext(AnswerTheQuestions.class, bean -> bean.setId(approachId));
            } catch (ExamNotFoundException | StudentNotFoundException | UnavailableExamException ex) {
                setContext(CreateApproach.class, bean -> bean.message = ex.getCode());
                return String.format("startApproach?title=%s&uuid=%s&faces-redirect=true", title, getUuid());
            }

            return String.format("answerTheQuestions?uuid=%s&faces-redirect=true", getUuid());
        });
    }
}
