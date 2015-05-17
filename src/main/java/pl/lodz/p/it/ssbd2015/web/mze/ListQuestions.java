package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamsServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean do strony z listą wszystkich użytkowników.
 * @author Created by Tobiasz Kowalski on 17.05.15.
 */
@ManagedBean(name = "listQuestionsMZE")
@RequestScoped
public class ListQuestions {

    @EJB
    private ExamsServiceRemote examsService;

    private List<QuestionEntity> questionEntities;

    @PostConstruct
    private void initializeModel(){
        questionEntities=examsService.findAllQuestions();
    }

    public List<QuestionEntity> getQuestionEntities(){
        return questionEntities;
    }
}
