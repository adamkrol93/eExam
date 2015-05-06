package pl.lodz.p.it.ssbd2015.entities.facade;

import javax.persistence.EntityManager;

/**
 * Interfejs bazowy dla Fasad, określa metody, które musza być zaimplementowane aby korzystać z interfejsów Create, Delete, Merge, Read.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>0
 */
public interface FacadeBase<K, E> {

    /**
     * Metoda zwraca obiekty typu Class, obiektu obiektu obsługiwanego przez Fasadę
     * @return Class na której pracuje fasada
     */
    Class<E> getEntityClass();

    /**
     * Zwraca EntityManagera, na którym pracuje Fasada
     * @return EntityManager fasady
     */
    EntityManager getEntityManager();

}
