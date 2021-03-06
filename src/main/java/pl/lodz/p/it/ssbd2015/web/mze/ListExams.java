package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamListServiceRemote;
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
 * Bean do wyświetlania listy wszystkich egzaminów
 * @author Adam Król
 * @author Andrzej Kurczewski
 * @author Bartosz Ignaczewski
 */
@ManagedBean(name = "listExamsMZE")
@ViewScoped
public class ListExams extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ExamListServiceRemote examListServiceRemote;

    private List<ExamEntity> examEntities;

    private transient DataModel<ExamEntity> exams;

    private String message;

    /**
     * Metoda korzysta z dobrodziejstw contextMapy i ustawia
     * odpowiednie identyfikator do przejścia do szczegółów egzaminu
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String gotoDetails() {
        long examId = exams.getRowData().getId();
        setContext(ShowExamDetails.class, bean -> bean.setId(examId));
        return String.format("showExam?uuid=%s&faces-redirect=true", getUuid());
    }

    /**
     * Metoda korzysta z dobrodziejstw contextMapy i ustawia
     * odpowiednie identyfikator do przejścia do raportu dotyczącego egzaminu
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String gotoReport() {
        long examId = exams.getRowData().getId();
        setContext(ShowExamDetails.class, bean -> bean.setId(examId));
        return String.format("showExamReport?uuid=%s&faces-redirect=true", getUuid());
    }

    /**
     * Metoda korzysta z dobrodziejstw contextMapy i ustawia
     * odpowiednie identyfikator do przejścia do strony edycji egzaminu
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String gotoEdit() {
        long examId = exams.getRowData().getId();
        setContext(EditExam.class, bean -> bean.setId(examId));
        return String.format("editExam?uuid=%s&faces-redirect=true", getUuid());
    }

    /**
     * Metoda odpowiada za sklonowanie egzaminu wraz z odswiezeniem strony z odpowiednia wiadomoscia
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String cloneExam() {
        return expectApplicationException(() -> {
            examListServiceRemote.cloneExam(exams.getRowData().getId());

            setContext(ListExams.class, (bean -> {
                bean.message = "mze.clone_exam.exam_cloned_message";
            }));
            return "listExams?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Metoda pobiera z endpointu ({@link ExamListServiceRemote} wszystkie egzaminy w systemie.
     */
    @PostConstruct
    private void initializeModel()
    {
        this.examEntities = examListServiceRemote.findAll();
    }


    public List<ExamEntity> getExamEntities() {
        return examEntities;
    }


    public DataModel<ExamEntity> getExams() {
        if(exams == null)
        {
            this.exams = new ListDataModel<>(examEntities);
        }
        return exams;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
