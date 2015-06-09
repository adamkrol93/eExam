package pl.lodz.p.it.ssbd2015.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa pomocnicza do widoków jsf, umożliwiajaca stworzenie tabelek, w których można zaznaczyć wiele rzędów.
 * @author Michał Sośnicki
 */
public class SelectableItem<T> implements Serializable {

    private T item;

    private boolean selected;

    public SelectableItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Funkcja przemieniająca dowolną kolekcję w listę zaznaczalnych obiektów, z których każdy opakowuje element
     * z przekazanej kolekcji.
     * @param iterable Coś, po czym można przejść, zbierając po drodze obiekty do opakowania.
     * @param <T> Typ obiektów z opakowywanej kolekcji.
     * @return Lista zawierająca elementy z argumentu, lecz opakowane w zaznaczalny obiekt.
     */
    public static <T> List<SelectableItem<T>> wrap(Iterable<T> iterable) {
        List<SelectableItem<T>> wrapped = new ArrayList<>();
        iterable.forEach(item -> wrapped.add(new SelectableItem<>(item)));
        return wrapped;
    }
}
