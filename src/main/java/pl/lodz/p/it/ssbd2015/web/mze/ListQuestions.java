package pl.lodz.p.it.ssbd2015.web.mze;


import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamsServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Backing bean do strony z listą wszystkich pytań.
 * @author Created by Tobiasz Kowalski on 17.05.15.
 */
@ManagedBean(name = "listQuestionsMZE")
@ViewScoped
public class ListQuestions extends BaseContextBean{

    private static final long serialVersionUID = 1L;

    @EJB
    private ExamsServiceRemote examsService;

    private List<QuestionEntity> questionEntities;

    private transient DataModel<QuestionEntity> questions;

    public String gotoEdit() {
        long examId = questions.getRowData().getId();
        setContext(EditQuestion.class, bean -> bean.setId(examId));
        return String.format("editQuestion?uuid=%s&faces-redirect=true", getUuid());
    }

    @PostConstruct
    private void initializeModel(){
        questionEntities=examsService.findAllQuestions();
    }

    public List<QuestionEntity> getQuestionEntities(){
        return questionEntities;
    }

    public DataModel<QuestionEntity> getQuestions() {
        if(questions == null)
        {
            this.questions = new ListDataModel<>(questionEntities);
        }
        return questions;
    }

    public static String cut(String message){

        if(message!=null && message.length()>70){
            return message.substring(0,70).concat("...");
        }

        return message;
    }
}
