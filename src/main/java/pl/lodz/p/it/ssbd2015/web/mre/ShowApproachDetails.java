package pl.lodz.p.it.ssbd2015.web.mre;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.mre.services.ApproachesServiceRemote;
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
 * Backing bean dla strony szczegółów podejścia, zarówno dla ucznia jak i opiekuna.
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "showApproachDetailsMRE")
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
     * Oblicza sumę punktów uzyskanych za podejście i zwraca ją.
     * @return Suma punktów uzyskanych za podejście
     */
    public long getPoints() {
        return new ArrayList<>(approach.getAnswers()).stream().map(AnswerEntity::getGrade).reduce(0, (a, b) -> a + b);
    }

    /**
     * Wyznacza klasę stylu bootstrapa używaną do wyświetlenia wyniku w zależności od procenta zdobytych punktów
     * @return klasa css bootstrapa
     */
    public String getResultStyle() {
        int result = (int) (3.2 * getPoints() / getMaxPoints());
        return styles[result];
    }

    /**
     * Oblicza maksymalną liczbę punktów możliwych do uzyskania za podejście
     * @return maksymalna liczba punktów możliwych do uzyskania za podejście
     */
    public long getMaxPoints() {
        return approach.getAnswers().size() * 2;
    }

    /**
     * Zwraca instancję klasy {@link Calendar}, reprezentującą obecny czas.
     * Wykorzystywana w celu renderowania odpowiedniej wiadomości o czasie zakończenia podejścia
     * @return instancja klasy {@link Calendar}, reprezentująca obecny czas.
     */
    public Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    /**
     * Zwraca listę intów, pozwalając na powtórzenie wyświetlenia jakiegoś elementu zadaną liczbę razy.
     * Niestety forEach wymaga podania kolekcji, w przeciwieństwie do tego znanego z JSTL, zatem jest
     * to drobne obejście tego problemu
     * @param times Liczba powtórzeń
     * @return lista kolejnych liczb całkowitych od 0 włącznie do {@code times} wyłącznie.
     */
    public List<Integer> repeat(int times) {
        return IntStream.range(0, times).boxed().collect(Collectors.toList());
    }
}
