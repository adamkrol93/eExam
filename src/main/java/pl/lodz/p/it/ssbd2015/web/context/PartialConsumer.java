package pl.lodz.p.it.ssbd2015.web.context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
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

    public static <T> PartialConsumer of(Class<T> clazz, Consumer<? super T> action) {
        return new PartialConsumer(clazz, action);
    }

    private PartialConsumer(Map<Class, Consumer> actions) {
        this.actions = actions;
    }

    @SuppressWarnings("unchecked")
    public void accept(Object t) {
        actions.getOrDefault(t.getClass(), idConsumer).accept(t);
    }

    public boolean accepts(Object t) {
        return actions.containsKey(t.getClass());
    }

    public PartialConsumer orElse(PartialConsumer other) {
        Map<Class, Consumer> newActions = new HashMap<>(other.actions);
        newActions.putAll(actions);
        return new PartialConsumer(newActions);
    }

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
