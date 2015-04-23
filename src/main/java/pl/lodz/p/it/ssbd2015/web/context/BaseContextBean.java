package pl.lodz.p.it.ssbd2015.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
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

    public void checkContext() {
        contextMap.applyContext(this);
        doInContext();
    }

    protected void doInContext() {
    }

    protected void resetUuid() {
        uuid = null;
    }

    protected PartialConsumer getContext() {
        return contextMap.getViewContext(uuid);
    }

    protected void setContext(PartialConsumer consumer) {
        logger.info("Setting new context using PartialConsumer");

        if (consumer == null) {
            resetContext();
        }
        else {
            contextMap.setViewContext(uuid, consumer);
        }
    }

    protected <T> void setContext(Class<T> clazz, Consumer<T> consumer) {
        logger.info("Setting new context using Consumer");

        if (consumer == null) {
            resetContext();
        }
        else {
            contextMap.setViewContext(uuid, PartialConsumer.of(clazz, consumer));
        }
    }

    protected void resetContext() {
        logger.info("Resetting context");

        contextMap.removeViewContext(uuid);
    }
}
