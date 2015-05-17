package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamsServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean dla strony egzaminatora, wyświetlającej szczegóły egzaminu
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "showExamDetailsMZE")
@ViewScoped
public class ShowExamDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ExamsServiceRemote examsService;

    private ExamEntity exam;

    private long id;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            exam = examsService.findById(id);
            resetContext();
        });
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }
}
