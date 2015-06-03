package pl.lodz.p.it.ssbd2015.web.context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Wersja {@link Consumer}, która nie musi być zdefiniowana dla wszystkich swoich argumentów.
 * Metoda akceptująca wykonuje na argumencie działanie odpowiednie dla argumentów jego typu, bądź nie robi
 * nic jeżeli takie działanie nie zostało zdefiniowane.
 * Zawiera kilka metod ułatwiających łączenie PartialConsumerów.
 * @author Michał Sośnicki
 */
public final class PartialConsumer {

    public final static PartialConsumer ID = new PartialConsumer();

    private final static Consumer idConsumer = (a) -> { };

    private final Map<Class, Consumer> actions;

    public PartialConsumer() {
        actions = new HashMap<>();
    }

    public <T> PartialConsumer(Class<T> clazz, Consumer<? super T> action) {
        actions = new HashMap<>();
        actions.put(clazz, action);
    }

    /**
     * Tworzy PartialConsumera zdefiniowanego dla pojedynczej klasy.
     * Można potem takich konsumentów połączyć.
     * @param clazz Klasa, dla której będzie zdefiniowany.
     * @param action Konsument obiektów takiej klasy.
     * @param <T> Parametr typu taki jak klasa clazz.
     * @return PartialConsumer zdefiniowany dla pojedynczej klasy.
     */
    public static <T> PartialConsumer of(Class<T> clazz, Consumer<? super T> action) {
        return new PartialConsumer(clazz, action);
    }

    private PartialConsumer(Map<Class, Consumer> actions) {
        this.actions = actions;
    }

    /**
     * Wykonuje na obiekcie działanie zdefiniowane dla obiektów jego klasy.
     * Jeżeli takiego działania nie mamy, nic nie zmienia.
     * @param t Obiekt do konsumpcji.
     */
    @SuppressWarnings("unchecked")
    public void accept(Object t) {
        actions.getOrDefault(t.getClass(), idConsumer).accept(t);
    }

    /**
     * Sprawdza, czy PartialConsumer jest zdefiniowany dla klasy danego obiektu, tzn. czy wykona jakąś akcję
     * na nim jakby go przekazać.
     * @param t Obiekt do konsumpcji.
     * @return Czy dla obiektu jest zdefiniowane jakieś działanie.
     */
    public boolean accepts(Object t) {
        return actions.containsKey(t.getClass());
    }

    /**
     * Łączy dwóch PartialConsumerów w ten sposób, iż dla obiektów, gdzie pierwszy z nich nie jest zdefiniowany,
     * zostanie wywołany drugi. Nie modyfikuje tego ani drugiego konsumenta, zwraca nowego.
     * @param other drugi PartialConsumer
     * @return PartialConsumer - połączenie pierwszego i drugiego PartialConsumera faworyzujące tego pierwszego.
     */
    public PartialConsumer orElse(PartialConsumer other) {
        Map<Class, Consumer> newActions = new HashMap<>(other.actions);
        newActions.putAll(actions);
        return new PartialConsumer(newActions);
    }

    /**
     * Tworzy pełnego Consumera typu T, który ma wszystkie definicje z tego, a we wszystkich pozostałych przypadkach
     * fallbackuje na konsumenta przekazanego w argumencie. Faworyzowane są definicje PartialConsumera.
     * @param consumer Konsument obiektów pewnej klasy.
     * @param <T> Typ dla którego zostanie dodana definicja.
     * @return Total Consumer, wykorzystujący definicje z tego PartialConsumera z fallbackiem do argumentu.
     */
    public <T> Consumer<T> orElse(Consumer<T> consumer) {
        return (T t) -> {
            if (accepts(t)) {
                accept(t);
            }
            else {
                consumer.accept(t);
            }
        };
    }

}
