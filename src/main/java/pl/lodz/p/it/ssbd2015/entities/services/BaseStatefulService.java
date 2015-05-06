package pl.lodz.p.it.ssbd2015.entities.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.SessionContext;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Bazowa klasa abstrakcyjna dla beanów Stateful wykorzystywany do tworzenia Endpointów.
 * Główne zadanie tej klasy to logowanie informacji o transkakcjach aplikacyjnych.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public abstract class BaseStatefulService {

    private static final AtomicLong txCounter = new AtomicLong();

    @Resource
    private SessionContext sessionContext;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private long txId;


    /**
     * Loguje rozpoczęcie transakcji aplikacyjnej
     */
    @AfterBegin
    private void logTransactionBegan() {
        txId = txCounter.getAndIncrement();
        String personLogin = sessionContext.getCallerPrincipal().getName();

        logger.info("Transaction(id={}, person={}) has begun.", txId, personLogin);
    }


    /**
     * Loguje zakończenie transakcji aplikacyjnej
     * @param committed
     */
    @AfterCompletion
    private void logTransactionEnded(boolean committed) {
        String personLogin = sessionContext.getCallerPrincipal().getName();
        String result = committed ? "committed" : "rolled back";

        logger.info("Transaction(id={}, person={}) has been {}.", txId, personLogin, result);
    }

}
