package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamListServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Bean do wyświetlania listy wszystkich egzaminów
 * @author Adam Król
 */
@ManagedBean(name = "listExamsMZE")
@ViewScoped
public class ListExams extends BaseContextBean {

    @EJB
    private ExamListServiceRemote examListServiceRemote;

    private List<ExamEntity> examEntities;

    /**
     * Metoda pobiera z endpointu ({@link ExamListServiceRemote} wszystkie egzaminy w systemie.
     * @return Lista wszystkich egzaminów
     */
    public List<ExamEntity> getExamEntities() {
        if (examEntities == null) {
            examEntities = examListServiceRemote.findAll();
        }
        return examEntities;
    }

}
