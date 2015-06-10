package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.moe.services.ApproachesServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Backing bean dla strony nauczyciela, wyświetlającej szczegóły podejścia
 *
 * @author Bartosz Ignaczewski
 */
@ManagedBean(name = "showApproachDetailsMOE")
@ViewScoped
public class ShowApproachDetails extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ApproachesServiceRemote approachesService;

    private ApproachEntity approach;

    private long id;

    private final String[] styles = {"danger", "warning", "info", "success"};

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = approachesService.findById(id);
        });
    }

    /**
     * Metoda sprawdzająca czy wszystkie odpowiedzi w danym podejściu zostały już sprawdzone czy też nie.
     * @return Zwraca kod wiadomości do wyświetlenia. Jeżeli podejście było już sprawdzone to: moe.list.approaches.to_evaluate.no jeżeli nie to: moe.list.approaches.to_evaluate.no
     */
    public String checkApproachIsToEvaluate() {
        if(approach.isDisqualification()){
            return "moe.show_approach_details.to_evaluate.disqualified";
        }

        if (Calendar.getInstance().before(approach.getDateEnd())) {
            return "moe.show_approach_details.to_evaluate.ongoing";
        }

        for (AnswerEntity answerEntity : approach.getAnswers()) {
            if (answerEntity.getTeacher() == null) {
                return "moe.show_approach_details.to_evaluate.yes";
            }
        }
        return "moe.show_approach_details.to_evaluate.no";
    }

    /**
     * Metoda zbiera punkty dla kazdej z odpowiedzi dla podejścia
     * @return Ilość zdobytych punktów w podejściu
     */
    public long getPoints() {
        return new ArrayList<>(approach.getAnswers()).stream().map(AnswerEntity::getGrade).reduce(0, (a, b) -> a + b);
    }

    /**
     * Metoda liczy maksymalną liczbę punktów którą można było uzyskać z podejścia
     * @return Maksymalna liczba punktów którą można było uzyskać z podejścia
     */
    public long getMaxPoints() {
        return approach.getAnswers().size() * 2;
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

    /**
     * Metoda odpowiada za sprowadzenie do listy Integerów sekwencyjnie uporządkowanej IntStream (wraz z redukcją zmiennych)
     * @param times Do jakiej wartości od 0 sprawdzać
     * @return Listę wartości (jeśli istnieją)
     */
    public List<Integer> repeat(int times) {
        return IntStream.range(0, times).boxed().collect(Collectors.toList());
    }

}
