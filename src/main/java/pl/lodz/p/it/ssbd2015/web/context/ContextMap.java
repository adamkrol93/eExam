package pl.lodz.p.it.ssbd2015.web.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Mapa UUID na PartialConsumerów, z mocno ograniczonym interfejsem, by możliwe było tylko dodawanie, usuwanie
 * wpisów i zaaplikowanie PartialConsumera do BaseContextBeana na podstawie tego, co ten bean zwraca w getUuid.
 * Przy okazji jest to bean sesyjny.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@ManagedBean(name = "contextMap")
@SessionScoped
public class ContextMap {

    private static final Logger logger = LoggerFactory.getLogger(ContextMap.class);

    private Map<UUID, PartialConsumer> viewContexts = new HashMap<>();

    /**
     * Odczytuje UUID i typ BaseContextBeana i na tej podstawie stara się odnaleźć dla niego Consumera, który
     * ustawi mu stan. Jeżeli BaseContextBean nie ma ustawionego UUID, to oczywiście poszukiwania nie mają sensu,
     * ale zostanie dla niego wygenerowany nowy UUID, by mógł dodać konteksty dla swoich podwidoków.
     * @param contextBean
     */
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

    /**
     * Zwraca PartialConsumera przechowywanego w mapie dla danego UUID.
     * @param uuid Klucz UUID
     * @return PartialConsumer - wartość pod danym kluczem
     */
    public PartialConsumer getViewContext(UUID uuid) {
        return viewContexts.get(uuid);
    }

    /**
     * Ustawia PartialConsumera w mapie pod danym UUID.
     * @param uuid UUID - klucz.
     * @param consumer PartialConsumer - wartość.
     */
    public void setViewContext(UUID uuid, PartialConsumer consumer) {
        viewContexts.put(uuid, consumer);
    }

    /**
     * Usuwa wpis w mapie pod danym kluczem, jeżeli taki istnieje.
     * @param uuid UUID - klucz.
     */
    public void removeViewContext(UUID uuid) {
        viewContexts.remove(uuid);
    }

}
