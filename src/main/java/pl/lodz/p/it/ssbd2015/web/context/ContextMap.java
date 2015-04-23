package pl.lodz.p.it.ssbd2015.web.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@ManagedBean(name = "contextMap")
@SessionScoped
public class ContextMap {

    private static final Logger logger = LoggerFactory.getLogger(ContextMap.class);

    private Map<UUID, PartialConsumer> viewContexts = new HashMap<>();

    public void applyContext(BaseContextBean contextBean) {
        logger.info("ViewContexts {} contain {} entries.", viewContexts, viewContexts.size());

        UUID uuid = contextBean.getUuid();
        logger.info("Applying context to {} with uuid {}", contextBean, uuid);

        if (uuid == null) {
            UUID newUuid = UUID.randomUUID();
            contextBean.setUuid(newUuid);

            logger.info("Assigned new uuid {} to {}", newUuid, contextBean);
        }
        else {
            viewContexts.getOrDefault(uuid, PartialConsumer.ID).accept(contextBean);

            logger.info("Applied context {} to {} with uuid {}.", viewContexts.get(uuid), contextBean, uuid);
        }
    }

    public PartialConsumer getViewContext(UUID uuid) {
        return viewContexts.get(uuid);
    }

    public void setViewContext(UUID uuid, PartialConsumer consumer) {
        viewContexts.put(uuid, consumer);
    }

    public void removeViewContext(UUID uuid) {
        viewContexts.remove(uuid);
    }

}
