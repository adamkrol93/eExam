package pl.lodz.p.it.ssbd2015.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.web.ApplicationErrorBean;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Klasa bazowa dla beanów stanowych, które mają współpracować z ContextMapą. Dodaje do nich pole do przechowywania
 * UUID i kilka metod, które będą korzystać w ContextMapy z kluczem równym UUID temu beana.
 * @author Michał Sośnicki
 */
public class BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @ManagedProperty(value="#{contextMap}")
    private ContextMap contextMap;

    private UUID uuid;

    public void setContextMap(ContextMap contextMap) {
        this.contextMap = contextMap;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Metoda, którą trzeba wywołać w .xhtmlu korzystającym z danego beana w f:viewAction action="#{bean.checkContext}"
     * Dzięki temu zostanie ona wywołana po ustawieniu viewParamów, a koniecznie musi być przed jej wywołaniem ustawiony
     * UUID tego widoku, jeżeli ma on na początku wyszukać swój kontekst.
     * Ustawienie UUID może wyglądać tak f:viewParam name="uuid" value="#{bean.uuid}" converter="uuidConverter"
     * Po ustawieniu kontekstu wywołuje metodę szablonową doInContext
     */
    public void checkContext() {
        contextMap.applyContext(this);
        doInContext();
    }

    /**
     * Metoda, która, przy spełenieniu wymagań dla checkContextu, zostanie wywołana przy tworzeniu beana. ale
     * po ustawieniu kontekstu oczekującego na niego w ContextMapie.
     */
    protected void doInContext() {
    }

    /**
     * Resetuje uuid tego beana. Dobrze to zrobić w doInContext, by dzieci tego beana otwarte na wielu kartach
     * dostały osobne UUID.
     */
    protected void resetUuid() {
        uuid = null;
    }

    /**
     * Zwraca PartialConsumera dla uuid z tego beana w contextMapie.
     * @return PartialConsumer dla widoków o uuid równym uuid temu beanowi.
     */
    protected PartialConsumer getContext() {
        return contextMap.getViewContext(uuid);
    }

    /**
     * Ustawia PartialConsumera dla widoków o uuid równym uuid tego beana, a zatem być może dla jego potomków.
     * @param consumer PartialConsument to ustawiania stanu w dzieciach tego beana.
     */
    protected void setContext(PartialConsumer consumer) {
        logger.info("Setting new context using PartialConsumer");

        if (consumer == null) {
            resetContext();
        }
        else {
            contextMap.setViewContext(uuid, consumer);
        }
    }

    /**
     * Przeładowanie setContextu przyjmującego PartialConsumera, które tworzy i ustawia PartialConsumera
     * zdefiniowanego dla tylko jednej klasy.
     * @param clazz Klasa oczekiwanego potomka.
     * @param consumer Konsument, który ustawia stan na potomkach danego typu.
     * @param <T> Typ oczekiwanego potomka.
     */
    protected <T> void setContext(Class<T> clazz, Consumer<T> consumer) {
        logger.info("Setting new context using Consumer");

        if (consumer == null) {
            resetContext();
        }
        else {
            contextMap.setViewContext(uuid, PartialConsumer.of(clazz, consumer));
        }
    }

    /**
     * Usuwa PartialConsumera przypisanego dla uuid tego beana z ContextMapy.
     */
    protected void resetContext() {
        logger.info("Resetting context");

        contextMap.removeViewContext(uuid);
    }

    /**
     * Wykonuje akcję przekazaną w argumencie. W razie wyjątku ApplicationBaseException przechwyuje go,
     * zwraca outcome string do strony /error/applicationError i ustawia w ContextMapie komunikat do wyświetlenia
     * na tej podstronie.
     * @param action Akcja do wykonania, która może zakończyć się ApplicationBaseException.
     * @return Outcome danej akcji lub do applicationError.
     */
    protected String expectApplicationException(ApplicationErrorProducer action) {
        String result;
        try {
            result = action.get();
        } catch (ApplicationBaseException e) {
            setContext(ApplicationErrorBean.class, bean -> bean.setExceptionMessage(e.getCode()));
            return "/error/applicationError?faces-redirect=true&uuid=" + getUuid();
        }

        return result;
    }

    /**
     * Wykonuje akcję przekazaną w argumencie. W razie wyjątku ApplicationBaseException przechwyuje go
     * i przekierowuje do strony /error/applicationError przez FacesContext i ustawia w ContextMapie
     * komunikat do wyświetlenia.error/applicationError.xhtml?uuid=ee7f5392-7c00-452b-a727-121a267900ae
     * @param action Akcja do wykonania, która może zakończyć się ApplicationBaseException.
     */
    protected void expectApplicationException(ApplicationErrorAction action) {
        try {
            action.run();
        } catch (ApplicationBaseException e) {
            setContext(ApplicationErrorBean.class, bean -> bean.setExceptionMessage(e.getCode()));
            try {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                String redirectResource = "/error/applicationError.xhtml?uuid=" + getUuid();
                externalContext.redirect(externalContext.getRequestContextPath() + redirectResource);
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    protected interface ApplicationErrorProducer {
        String get() throws ApplicationBaseException;
    }

    protected interface ApplicationErrorAction {
        void run() throws ApplicationBaseException;
    }

}
